package myProgress.repository;

import myProgress.model.User;
import myProgress.model.UserAccessRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface CrudUserAccessRightRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u from UserAccessRight u WHERE u.user.id=:access_right and u.accessRight=:user_id")
    UserAccessRight getAccessAllowed(@Param("user_id") int userId, @Param("access_right") int userProgressId);
}
