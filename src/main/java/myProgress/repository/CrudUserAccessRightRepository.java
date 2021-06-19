package myProgress.repository;

import myProgress.model.UserAccessRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserAccessRightRepository extends JpaRepository<UserAccessRight, Integer> {

    @Query("SELECT u.user.id FROM UserAccessRight u WHERE u.accessRight=?1")
    List<Integer> getSubscriptions(int subscriberId);
}
