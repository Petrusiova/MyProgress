package myProgress.repository;

import myProgress.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);

    @EntityGraph(attributePaths = {"subscribers"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithSubscribers(int id);

    @EntityGraph(attributePaths = {"subscriptions"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithSubscriptions(int id);

    @EntityGraph(attributePaths = {"measurements"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithMeasurements(int id);
}
