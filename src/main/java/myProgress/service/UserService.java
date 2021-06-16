package myProgress.service;

import myProgress.model.Following;
import myProgress.model.User;
import myProgress.model.UserAccessRight;
import myProgress.repository.CrudFollowingRepository;
import myProgress.repository.CrudUserAccessRightRepository;
import myProgress.repository.CrudUserRepository;
import myProgress.util.exception.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;

import static myProgress.util.ValidationUtil.checkNotFound;
import static myProgress.util.ValidationUtil.checkNotFoundWithId;


@Service
public class UserService {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;
    private final CrudUserAccessRightRepository accessRightRepository;
    private final CrudFollowingRepository followingRepository;

    public UserService(CrudUserRepository repository,
                       CrudUserAccessRightRepository accessRightRepository,
                       CrudFollowingRepository followingRepository) {
        this.crudUserRepository = repository;
        this.accessRightRepository = accessRightRepository;
        this.followingRepository = followingRepository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public boolean delete(int id) {
        checkNotFoundWithId(crudUserRepository.delete(id) != 0, id);
        return true;
    }

    public User get(int id) {
        return crudUserRepository.findById(id).orElseThrow(() -> new NotFoundException("No user with id = " + id));
    }

    public User getWithMeasurements(int id) {
        return crudUserRepository.getWithMeasurements(id);
    }

    public User getWithAccessUserIds(int id) {
        return crudUserRepository.getWithAccessAllowedIds(id);
    }

    public User getWithFollowings(int id) {
        return crudUserRepository.getWithFollowings(id);
    }

    public User getByEmail(String email) {
        return checkNotFound(crudUserRepository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) { // TODO: 09.03.2021 needs to be refactored (passwordEncoder, email to lower case)
        checkNotFoundWithId(crudUserRepository.save(user), user.getId());
    }

    
    /**
     * 1 - adding a subscription (to client's or friend's progress) to following user
     * 2 - adding a follower/subscriber (with possibility to see) to following user
     */
    @CacheEvict(value = "users", allEntries = true)
    public void grantAccessToUser(int progressOwnerId, int subscriberId) {
        User subscriber = crudUserRepository.getWithFollowings(subscriberId);
        Following newFollowing = followingRepository.save(new Following(subscriber, progressOwnerId));
        crudUserRepository.addFollowing(subscriber, newFollowing);

        User progressOwner = crudUserRepository.getWithAccessAllowedIds(progressOwnerId);
        UserAccessRight newUserAccessRight = accessRightRepository.save(new UserAccessRight(progressOwner, subscriberId));
        crudUserRepository.grantAccessToUser(progressOwner, newUserAccessRight);

    }
}