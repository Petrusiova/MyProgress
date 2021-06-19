package myProgress.web.user;

import myProgress.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static myProgress.web.SecurityUtil.authUserId;


@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractUserController {

    public static final String REST_URL = "/rest/profile";

    @GetMapping
    public User get() {
        return super.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(authUserId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        super.update(user, authUserId());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void grantAccessToUser(@PathVariable int id){
        super.grantAccessToUser(id);
    }

    @GetMapping("/with-measurements")
    public User getWithMeasurements() {
        return super.getWithMeasurements(authUserId());
    }

    @GetMapping("/with-accessUserIds")
    public User getWithAccessUserIds() {
        return super.getWithUserAccessRights(authUserId());
    }

    @GetMapping("/with-followings")
    public List<Integer> getSubscriptions() {
        return super.getSubscriptions(authUserId());
    }
}