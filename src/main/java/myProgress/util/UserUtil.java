package myProgress.util;

import myProgress.model.Role;
import myProgress.model.User;
import myProgress.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }
}
