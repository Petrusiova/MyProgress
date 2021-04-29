package myProgress.service;

import myProgress.MeasurementTestData;
import myProgress.UserTestData;
import myProgress.model.Role;
import myProgress.model.User;
import myProgress.model.UserAccessRight;
import myProgress.util.exception.NotFoundException;
import org.junit.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static myProgress.MeasurementTestData.M_MATCHER;
import static myProgress.UserTestData.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class UserServiceTest extends AbstractServiceTest{

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User created = service.create(getNew());
        Integer newId = created.getId();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = service.get(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
    }

    @Test
    public void getWithMeasurements() {
        User user = service.getWithMeasurements(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
        M_MATCHER.assertMatch(user.getMeasurements(), MeasurementTestData.userMeasurements);
    }

    @Test
    public void getWithAccessUserIds() {
        User admin = service.getWithAccessUserIds(USER_ID + 1);
        USER_MATCHER.assertMatch(admin, UserTestData.admin);
        assertEquals(
                admin.getAccessUserIds().stream().map(UserAccessRight::getAccessRight).collect(Collectors.toSet()),
                Set.of(USER_ID, USER_ID + 1));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, admin, user);
    }

    @Test
    public void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User", "  ", "password", Role.USER)));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)));
    }
}