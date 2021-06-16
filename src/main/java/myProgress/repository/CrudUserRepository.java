package myProgress.repository;

import myProgress.model.Following;
import myProgress.model.User;
import myProgress.model.UserAccessRight;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);

    @EntityGraph(attributePaths = {"userAccessRights"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithAccessAllowedIds(int id);

    @EntityGraph(attributePaths = {"followings"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithFollowings(int id);

    @EntityGraph(attributePaths = {"measurements"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithMeasurements(int id);

//    default User grantAccessToUser(User subscribed, int subscribing) {
//        Set<UserAccessRight> userAccessRights = subscribed.getUserAccessRights() == null ?
//                new HashSet<>() : subscribed.getUserAccessRights();
//        userAccessRights.add(new UserAccessRight(subscribed, subscribed.getId()));
//        userAccessRights.add(new UserAccessRight(subscribed, subscribing));
//        subscribed.setUserAccessRights(userAccessRights);
//        return subscribed;
//    }

    default User grantAccessToUser(User subscribed, UserAccessRight userAccessRight) {
        Set<UserAccessRight> userAccessRights = subscribed.getUserAccessRights() == null ?
                new HashSet<>() : subscribed.getUserAccessRights();
        userAccessRights.add(userAccessRight);
        subscribed.setUserAccessRights(userAccessRights);
        return subscribed;
    }

//    default User addFollowing(User subscribing, int subscribed) {
//        Set<Following> followings = subscribing.getFollowings() == null ?
//                new HashSet<>() : subscribing.getFollowings();
//        followings.add(new Following(subscribing, subscribing.getId()));
//        followings.add(new Following(subscribing, subscribed));
//        subscribing.setFollowings(followings);
//        return subscribing;
//    }

    default User addFollowing(User follower, Following subscription) {
        Set<Following> subscriptions = follower.getFollowings() == null ?
                new HashSet<>() : follower.getFollowings();
        subscriptions.add(subscription);
        follower.setFollowings(subscriptions);
        return follower;
    }
}
