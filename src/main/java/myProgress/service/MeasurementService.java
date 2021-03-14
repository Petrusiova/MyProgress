package myProgress.service;

import myProgress.model.Measurement;
import myProgress.repository.CrudMeasurementRepository;
import myProgress.repository.CrudUserAccessRightRepository;
import myProgress.repository.CrudUserRepository;
import myProgress.util.exception.IllegalMeasurementAccessException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static myProgress.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MeasurementService {

    private final CrudMeasurementRepository mRepository;
    private final CrudUserAccessRightRepository uarRepository;

    public MeasurementService(CrudMeasurementRepository repository, CrudUserAccessRightRepository uarRepository) {
        this.mRepository = repository;
        this.uarRepository = uarRepository;
    }

    public Measurement get(int id, int userId) {
        return checkNotFoundWithId(mRepository.get(id, userId), id);
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
        checkNotFoundWithId(mRepository.delete(id, userId), id);
    }

    public List<Measurement> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return mRepository.getBetween(userId, startDate, endDate);
    }

    public List<Measurement> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate,
                                        int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
        return mRepository.getBetween(userProgressId, startDate, endDate);
    }

    public Measurement create(Measurement measurement, int userId) {
        Assert.notNull(measurement, "measurement must not be null");
        measurement.setUser(uarRepository.getOne(userId));
        return mRepository.save(measurement);
    }

    public void update(Measurement measurement, int userId) {
        Assert.notNull(measurement, "measurement must not be null");
        if (!measurement.isNew() && get(measurement.getId(), userId) == null) {
            throw new IllegalMeasurementAccessException();
        }
        measurement.setUser(uarRepository.getOne(userId));
        checkNotFoundWithId(mRepository.save(measurement), measurement.getId());
    }

    private void checkAccessAllowed(int userId, int userProgressId) {
        if (uarRepository.getAccessAllowed(userId, userProgressId) == null) {
            throw new IllegalMeasurementAccessException("Access denied");
        }
    }
}