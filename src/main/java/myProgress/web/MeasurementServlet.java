package myProgress.web;

import myProgress.model.Measurement;
import myProgress.web.measurement.MeasurementRestController;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static myProgress.util.DateUtil.parseLocalDate;

public class MeasurementServlet extends HttpServlet {
    
    private MeasurementRestController mRestController;

    @Override
    public void init() {
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        mRestController = springContext.getBean(MeasurementRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Measurement measurement = new Measurement(
                LocalDate.parse(request.getParameter("date")),
                Double.valueOf(request.getParameter("weight")),
                Double.valueOf(request.getParameter("waist")),
                Double.valueOf(request.getParameter("hips")));

        String shoulders = request.getParameter("shoulders");
        String quad = request.getParameter("quad");
        String bicep = request.getParameter("bicep");
        String avgCalories = request.getParameter("avgCalories");
        String trainingCount = request.getParameter("trainingCount");
        String avgSteps = request.getParameter("avgSteps");
        
        if (StringUtils.isEmpty(shoulders)) {
            measurement.setShoulders(Double.valueOf(shoulders));
        }
        
        if (StringUtils.isEmpty(quad)) {
            measurement.setQuad(Double.valueOf(quad));
        }
        
        if (StringUtils.isEmpty(bicep)) {
            measurement.setBicep(Double.valueOf(bicep));
        }
        
        if (StringUtils.isEmpty(avgCalories)) {
            measurement.setAvgCalories(Integer.valueOf(avgCalories));
        }
        
        if (StringUtils.isEmpty(trainingCount)) {
            measurement.setTrainingCount(Integer.valueOf(trainingCount));
        }
        
        if (StringUtils.isEmpty(avgSteps)) {
            measurement.setAvgSteps(Integer.valueOf(avgSteps));
        }
        
        if (StringUtils.isEmpty(request.getParameter("id"))) {
            mRestController.create(measurement);
        } else {
            mRestController.update(measurement, getId(request));
        }
        response.sendRedirect("measurements");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                mRestController.delete(id);
                response.sendRedirect("measurements");
                break;
            case "create":
            case "update":
                final Measurement Measurement = "create".equals(action) ?
                        new Measurement(LocalDate.now(), 0d, 0d, 0d) :
                        mRestController.get(getId(request));
                request.setAttribute("measurement", Measurement);
                request.getRequestDispatcher("/measurementForm.jsp").forward(request, response);
                break;
            case "filter":
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));

                request.setAttribute("measurements", mRestController.getBetween(startDate, endDate));
                request.getRequestDispatcher("/measurements.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("measurements", mRestController.getAll());
                request.getRequestDispatcher("/measurements.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
