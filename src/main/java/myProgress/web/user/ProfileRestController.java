package myProgress.web.user;

import myProgress.model.User;
import myProgress.web.SecurityUtil;
import org.springframework.stereotype.Controller;


@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(SecurityUtil.authUserId());
    }


    public User getWithMeasurements() {
        return super.getWithMeasurements(SecurityUtil.authUserId());
    }

    public User getWithAccessUserIds() {
        return super.getWithAccessUserIds(SecurityUtil.authUserId());
    }

    public void delete() {
        super.delete(SecurityUtil.authUserId());
    }

    public void update(User user) {
        super.update(user, SecurityUtil.authUserId());
    }

    public User grantAccessToUser(int id){
        return super.grantAccessToUser(id);
    }
}