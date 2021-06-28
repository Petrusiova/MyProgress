package myProgress.service;

import myProgress.model.User;
import myProgress.repository.CrudUserRepository;
import myProgress.util.exception.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import java.util.List;

import static myProgress.util.ValidationUtil.checkNotFound;
import static myProgress.util.ValidationUtil.checkNotFoundWithId;


@Service
public class UserService {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;

    public UserService(CrudUserRepository repository) {
        this.crudUserRepository = repository;
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

    public User getWithSubscribers(int id) {
        return crudUserRepository.getWithSubscribers(id);
    }

    public User getWithSubscriptions(int id) {
        return crudUserRepository.getWithSubscriptions(id);
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

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void grantAccessToUser(int currentUserId, int subscriberId) {
        User currentUser = crudUserRepository.getWithSubscribers(currentUserId);
        User subscriber = crudUserRepository.getOne(subscriberId);
        currentUser.getSubscribers().add(subscriber);
        crudUserRepository.save(currentUser);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
//        repository.save(user);  // !! need only for JDBC implementation
    }
}