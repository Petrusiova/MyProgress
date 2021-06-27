package myProgress.web.user;

import myProgress.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import myProgress.service.UserService;

import java.util.List;

import static myProgress.util.ValidationUtil.assureIdConsistent;
import static myProgress.util.ValidationUtil.checkNew;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }


    public User getWithMeasurements(int id) {
        log.info("getWithMeasurements {}", id);
        return service.getWithMeasurements(id);
    }

    public User getWithSubscribers(int id) {
        log.info("getWithSubscribers {}", id);
        return service.getWithSubscribers(id);
    }

    public void grantAccessToUser(int currentUserId, int subscriberId){
        service.grantAccessToUser(currentUserId, subscriberId);
    }

    public User getWithSubscriptions(int id) {
        log.info("getWithSubscriptions {}", id);
        return service.getWithSubscriptions(id);
    }
}