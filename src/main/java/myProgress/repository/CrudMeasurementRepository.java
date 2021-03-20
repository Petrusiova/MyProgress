package myProgress.repository;

import myProgress.model.Measurement;
import myProgress.model.UserAccessRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Measurement m WHERE m.id=:id and m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Query("SELECT m from Measurement m WHERE m.id=:id and m.user.id=:user_id")
    Measurement get(@Param("id") int id, @Param("user_id") int userId);

    @Query("SELECT m from Measurement m JOIN FETCH m.user WHERE m.id=:id and m.user.id=:user_id")
    Measurement getWithUser(@Param("id") int id, @Param("user_id") int userId);

    @Query("SELECT m from Measurement m WHERE m.user.id=:user_id ORDER BY m.date desc")
    List<Measurement> getAll(@Param("user_id") int userId);

    @Query("SELECT m from Measurement m WHERE m.user.id=:user_id AND m.date >= :start_date " +
            "and m.date <= :end_date ORDER BY m.date desc")
    List<Measurement> getBetween(@Param("user_id") int userId,
                                 @Param("start_date") LocalDate startDate,
                                 @Param("end_date") LocalDate endDate);

    @Query("SELECT u from UserAccessRight u WHERE u.user.id=:access_right and u.accessRight=:user_id")
    UserAccessRight getAccessAllowed(@Param("user_id") int userId, @Param("access_right") int userProgressId);
}
