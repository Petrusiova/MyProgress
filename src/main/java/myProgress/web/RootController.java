package myProgress.web;

import myProgress.model.User;
import myProgress.service.MeasurementService;
import myProgress.service.UserService;
import myProgress.to.MeasurementTo;
import myProgress.util.MeasurementsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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

    @GetMapping("/subscriptions")
    public String getSubscriptions(Model model) {

        Set<User> subscriptions = userService.getWithSubscriptions(SecurityUtil.authUserId()).getSubscriptions();
        Map<String, List<MeasurementTo>> measurementMap = new HashMap<>();

        if (subscriptions != null) {
            subscriptions.forEach(
                    item -> measurementMap.put(
                            item.getName(),
                            MeasurementsUtil.getTos(measurementService.getAll(SecurityUtil.authUserId(), item.getId()))));
        }

        model.addAttribute("subscriptions", measurementMap); // м б MeasurementsUtil.getTos?
        return "subscriptions";
    }
}
