package myProgress.repository;

import myProgress.model.User;
import myProgress.model.UserAccessRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudUserAccessRightRepository extends JpaRepository<UserAccessRight, Integer> {
}
