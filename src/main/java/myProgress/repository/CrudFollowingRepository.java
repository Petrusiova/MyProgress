package myProgress.repository;

import myProgress.model.Following;
import myProgress.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudFollowingRepository extends JpaRepository<Following, Integer> {
}
