package myProgress.service;

import myProgress.MeasurementTestData;
import myProgress.model.Measurement;
import myProgress.util.exception.IllegalMeasurementAccessException;
import myProgress.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static myProgress.MeasurementTestData.*;
import static myProgress.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MeasurementServiceTest extends AbstractServiceTest {

    @Autowired
    private MeasurementService service;

    @Test
    void create() throws Exception {
        Measurement newMeasurement = MeasurementTestData.getNew();
        Measurement created = service.create(newMeasurement, USER_ID);

        Integer newId = created.getId();
        newMeasurement.setId(newId);

        M_MATCHER.assertMatch(created, newMeasurement);
        M_MATCHER.assertMatch(service.get(newId, USER_ID), newMeasurement);
    }

    @Test
    void createDuplicateUserAndDate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new Measurement(LocalDate.of(2021, 3, 9), 1d, 1d, 1d), USER_ID));
    }

    @Test
    void get() {
        Measurement actual = service.get(M_ID, USER_ID);
        M_MATCHER.assertMatch(actual, measurement1);
    }

    @Test
    void getWithUser() {
        Measurement actual = service.getWithUser(M_ID, USER_ID);
        M_MATCHER.assertMatch(actual, measurement1);
        USER_MATCHER.assertMatch(actual.getUser(), user);
    }

    @Test
    void getAnotherUserProgress() {
        Measurement actual = service.get(M_ADMIN_ID, USER_ID, ADMIN_ID);
        M_MATCHER.assertMatch(actual, measurement4);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(0, USER_ID));
    }

    @Test
    void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(M_ID, ADMIN_ID));
    }

    @Test
    void getAll() {
        M_MATCHER.assertMatch(service.getAll(USER_ID), userMeasurements);
    }

    @Test
    void getAllAnotherUserProgress() {
        M_MATCHER.assertMatch(service.getAll(USER_ID, ADMIN_ID), measurement4);
    }

    @Test
    void getAllNotAllowed() {
        assertThrows(IllegalMeasurementAccessException.class, () -> service.getAll(M_ID, 0));
    }

    @Test
    void getBetween() {
        M_MATCHER.assertMatch(service.getBetween(
                LocalDate.of(2021, 2, 22),
                LocalDate.of(2021, 3, 1), USER_ID),
                measurement2, measurement3);
    }

    @Test
    void getBetweenAnotherUserProgress() {
        M_MATCHER.assertMatch(service.getBetween(
                LocalDate.of(2021, 2, 22),
                LocalDate.of(2021, 3, 1), ADMIN_ID),
                measurement4);
    }

    @Test
    void getBetweenWithNullDates() {
        M_MATCHER.assertMatch(service.getBetween(null, null, USER_ID), userMeasurements);
    }

    @Test
    void update() {
        Measurement updated = MeasurementTestData.getUpdated();
        service.update(updated, USER_ID);
        M_MATCHER.assertMatch(service.get(M_ID, USER_ID), MeasurementTestData.getUpdated());
    }

    @Test
    void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(measurement1, ADMIN_ID));
        Assertions.assertEquals("Not found entity with id=" + M_ID, exception.getMessage());
        M_MATCHER.assertMatch(service.get(M_ID, USER_ID), measurement1);
    }

    @Test
    void delete() {
        service.delete(M_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(M_ID, USER_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(0, USER_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(M_ID, ADMIN_ID));
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Measurement(null, LocalDate.now(), null, 58d, 86d), USER_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Measurement(null, null, 48d, 58d, 86d), USER_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Measurement(null, LocalDate.now(), 48.5, null, 86d), USER_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Measurement(null, LocalDate.now(), 48.85, 58d, null), USER_ID));
    }
}
