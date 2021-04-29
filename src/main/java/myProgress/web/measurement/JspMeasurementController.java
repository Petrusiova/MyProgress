package myProgress.web.measurement;

import myProgress.model.Measurement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

import static myProgress.util.DateUtil.parseLocalDate;

@Controller
@RequestMapping("/measurements")
public class JspMeasurementController extends AbstractMeasurementController{

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/measurements";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("measurement", super.get(getId(request)));
        return "measurementForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("measurement", new Measurement(LocalDate.now(), 0d, 0d, 0d));
        return "measurementForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Measurement measurement = new Measurement(
                LocalDate.parse(request.getParameter("date")),
                Double.parseDouble(request.getParameter("weight")),
                Double.parseDouble(request.getParameter("waist")),
                Double.parseDouble(request.getParameter("hips")));

            String shoulders = request.getParameter("shoulders");
            String quad = request.getParameter("quad");
            String bicep = request.getParameter("bicep");
            String avgCalories = request.getParameter("avgCalories");
            String trainingCount = request.getParameter("trainingCount");
            String avgSteps = request.getParameter("avgSteps");

            if (!(shoulders.isEmpty() && shoulders.isBlank())) {
                measurement.setShoulders(Double.valueOf(shoulders));
            }

            if (!(quad.isEmpty() && quad.isBlank())) {
                measurement.setQuad(Double.valueOf(quad));
            }

            if (!(bicep.isEmpty() && bicep.isBlank())) {
                measurement.setBicep(Double.valueOf(bicep));
            }

            if (!(avgCalories.isEmpty() && avgCalories.isBlank())) {
                measurement.setAvgCalories(Integer.valueOf(avgCalories));
            }

            if (!(trainingCount.isEmpty() && trainingCount.isBlank())) {
                measurement.setTrainingCount(Integer.valueOf(trainingCount));
            }

            if (!(avgSteps.isEmpty() && avgSteps.isBlank())) {
                measurement.setAvgSteps(Integer.valueOf(avgSteps));
            }


        if (request.getParameter("id").isEmpty()) {
            super.create(measurement);
        } else {
            super.update(measurement, getId(request));
        }
        return "redirect:/measurements";
    }

    @GetMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));

        model.addAttribute("measurements", super.getBetween(startDate, endDate));
        return "measurements";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
