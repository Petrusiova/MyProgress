package myProgress.service;

import myProgress.model.Measurement;
import myProgress.repository.UserRepository;
import myProgress.util.exception.IllegalMeasurementAccessException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import myProgress.repository.MeasurementRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static myProgress.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MeasurementService {

    private final MeasurementRepository mRepository;
    private final UserRepository userRepository;

    public MeasurementService(MeasurementRepository repository, UserRepository userRepository) {
        this.mRepository = repository;
        this.userRepository = userRepository;
    }

    public Measurement get(int id, int userId) {
        return checkNotFoundWithId(mRepository.get(id, userId), id);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(mRepository.delete(id, userId), id);
    }

    public List<Measurement> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return mRepository.getBetween(startDate, endDate, userId);
    }

    public List<Measurement> getAll(int userId) {
        return mRepository.getAll(userId);
    }

    public void update(Measurement meal, int userId) {
        checkNotFoundWithId(mRepository.save(meal, userId), meal.getId());
    }

    public Measurement create(Measurement meal, int userId) {
        return mRepository.save(meal, userId);
    }

    public Measurement get(int id, int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
        return checkNotFoundWithId(mRepository.get(id, userId, userProgressId), id);
    }

    public List<Measurement> getAll(int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
        return mRepository.getAll(userId, userProgressId);
    }

    public List<Measurement> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate,
                                        int userId, int userProgressId) {
        checkAccessAllowed(userId, userProgressId);
    return mRepository.getBetween(startDate, endDate, userId, userProgressId);
    }

    private void checkAccessAllowed(int userId, int userProgressId) {
        if (userId != userProgressId) {
            Set<Integer> accessUserIds = userRepository.get(userProgressId).getAccessUserIds();
            if (!(accessUserIds != null && accessUserIds.contains(userId))) {
                throw new IllegalMeasurementAccessException("Access denied");
            }
        }
    }
}