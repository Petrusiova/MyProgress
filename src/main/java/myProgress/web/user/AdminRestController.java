package myProgress.web.user;

import myProgress.model.User;
import myProgress.web.SecurityUtil;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminRestController extends AbstractUserController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    public User getWithMeasurements() {
        return super.getWithMeasurements(SecurityUtil.authUserId());
    }

    public User getWithAccessUserIds() {
        return super.getWithAccessUserIds(SecurityUtil.authUserId());
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }

    public User grantAccessToUser(int id){
        return super.grantAccessToUser(id);
    }
}