package myProgress.repository.inmemory;


import myProgress.UserTestData;
import myProgress.model.User;
import org.springframework.stereotype.Repository;
import myProgress.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        map.put(UserTestData.USER_ID, UserTestData.user);
        map.put(UserTestData.ADMIN_ID, UserTestData.admin);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }

    public boolean checkAccessForUser(int currentUserId, int userProgressId){
        User user = getCollection().stream()
                .filter(item -> item.getId().equals(userProgressId))
                .findFirst()
                .orElse(null);

        return user != null && user.getAccessUserIds().contains(currentUserId);
    }
}