package myProgress.web;

import myProgress.model.Measurement;
import myProgress.service.MeasurementService;
import myProgress.service.UserService;
import myProgress.util.MeasurementsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private MeasurementService measurementService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:measurements";
    }

    @GetMapping("/measurements")
    public String getMeasurements(Model model) {
        model.addAttribute(
                "measurements",
                MeasurementsUtil.getTos(measurementService.getAll(SecurityUtil.authUserId())));
        return "measurements";
    }

//    @GetMapping("/subscriptions")
//    public String getSubscriptions(Model model) {
//
//        List<Integer> subscriptionIds = userService.getSubscriptions(SecurityUtil.authUserId());
//        List<List<Measurement>> subscriptionsMeasurements =
//                subscriptionIds.stream().map(item -> userService.get(item).getMeasurements()).collect(Collectors.toList());
//
//        model.addAttribute(
//                "subscriptions",
//                subscriptionsMeasurements); // м б MeasurementsUtil.getTos?
//        return "measurements";
//    }
}
