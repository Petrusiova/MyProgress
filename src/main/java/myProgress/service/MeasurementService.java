package myProgress.service;

import myProgress.model.Measurement;
import myProgress.model.User;
import myProgress.repository.CrudMeasurementRepository;
import myProgress.repository.CrudUserRepository;
import myProgress.util.DateUtil;
import myProgress.util.exception.IllegalMeasurementAccessException;
import myProgress.util.exception.NotFoundException;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArray;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static myProgress.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MeasurementService {

    private final CrudMeasurementRepository mRepository;
    private final CrudUserRepository userRepository;

    public MeasurementService(CrudMeasurementRepository repository, CrudUserRepository userRepository) {
        this.mRepository = repository;
        this.userRepository = userRepository;
    }

    public Measurement get(int id, int userId) {
        return checkNotFoundWithId(mRepository.get(id, userId), id);
    }

    public Measurement getWithUser(int id, int userId) {
        return checkNotFoundWithId(mRepository.getWithUser(id, userId), id);
    }

    public Measurement get(int id, int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
        return checkNotFoundWithId(mRepository.get(id, userProgressId), id);
    }

    public List<Measurement> getAll(int userId) {
        return mRepository.getAll(userId);
    }

    public List<Measurement> getAll(int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
        return mRepository.getAll(userProgressId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(mRepository.delete(id, userId) != 0, id);
    }

    public List<Measurement> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return mRepository.getBetween(
                userId,
                DateUtil.currentDateOrMin(startDate),
                DateUtil.currentDateOrMax(endDate));
    }

    public List<Measurement> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate,
                                        int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
        return mRepository.getBetween(userProgressId,
                DateUtil.currentDateOrMin(startDate),
                DateUtil.currentDateOrMax(endDate));
    }

    public Measurement create(Measurement measurement, int userId) {
        Assert.notNull(measurement, "measurement must not be null");
        measurement.setUser(userRepository.getOne(userId));
        return mRepository.save(measurement);
    }

    public void update(Measurement measurement, int userId) {
        Assert.notNull(measurement, "measurement must not be null");
        if (!measurement.isNew() && get(measurement.getId(), userId) == null) {
            throw new IllegalMeasurementAccessException();
        }
        measurement.setUser(userRepository.getOne(userId));
        checkNotFoundWithId(mRepository.save(measurement), measurement.getId());
    }

    private void checkAccessAllowed(int userId, int userProgressId) {
        Optional<User> subscriber = userRepository.findById(userId);
        if (subscriber.isPresent()) {
            Optional<User> byProgressId = userRepository.findById(userProgressId);
            if (!(byProgressId.isPresent() &&
                    userRepository.getWithSubscribers(userProgressId).getSubscribers().contains(subscriber.get()))) {
                    throw new IllegalMeasurementAccessException("Access denied");
            }
        }
        else {
            throw new NotFoundException("No user found with id = " + userId);
        }
    }
}