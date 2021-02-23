package myProgress.repository;


import myProgress.model.Measurement;

import java.time.LocalDate;
import java.util.List;

public interface MeasurementRepository {
    // null if updated measurement do not belong to userId
    Measurement save(Measurement measurement, int userId);

    // false if measurement do not belong to userId
    boolean delete(int id, int userId);

    // null if measurement do not belong to userId
    Measurement get(int id, int userId);

    // null if measurement do not belong to userId or userId not included in followers' list
    Measurement get(int id, int userId, int userProgressId);

    // ORDERED dateTime desc
    /**
     * get all current user's measurements
     **/
    List<Measurement> getAll(int userId);
    
    // ORDERED dateTime desc
    /**
     * get all reviewed user's measurements
     * @param userProgressId - reviewed user's id
     **/
    List<Measurement> getAll(int userId, int userProgressId);

    // ORDERED dateTime desc
    List<Measurement> getBetween(LocalDate startDate, LocalDate endDate, int userId);

    // ORDERED dateTime desc
    List<Measurement> getBetween(LocalDate startDate, LocalDate endDate, int userId, int userProgressId);
}
