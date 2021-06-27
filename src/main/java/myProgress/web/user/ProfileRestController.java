package myProgress.web.user;

import myProgress.model.User;
import myProgress.web.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/grant_access/{subscriberId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void grantAccessToUser(
//            @AuthenticationPrincipal User currentUser,
            @PathVariable int subscriberId){
        super.grantAccessToUser(SecurityUtil.authUserId(), subscriberId);
    }

    @GetMapping("/with-measurements")
    public User getWithMeasurements() {
        return super.getWithMeasurements(authUserId());
    }

    @GetMapping("/with-subscribers")
    public User getWithSubscribers() {
        return super.getWithSubscribers(authUserId());
    }

    @GetMapping("/with-subscriptions")
    public User getWithSubscriptions() {
        return super.getWithSubscriptions(authUserId());
    }
}