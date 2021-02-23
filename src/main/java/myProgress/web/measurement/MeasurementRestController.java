package myProgress.web.measurement;

import lombok.AllArgsConstructor;
import myProgress.model.Measurement;
import myProgress.service.MeasurementService;
import myProgress.to.MeasurementTo;
import myProgress.util.MeasurementsUtil;
import myProgress.util.ValidationUtil;
import myProgress.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class MeasurementRestController {
    private static final Logger log = LoggerFactory.getLogger(MeasurementRestController.class);

    private final MeasurementService service;

    public Measurement get(int id){
        int userId = SecurityUtil.authUserId();
        log.info("get personal measurement {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public Measurement get(int id, int userProgressId){
        int userId = SecurityUtil.authUserId();
        log.info("user {} requests for measurement {} for user {}", userId, id, userProgressId);
        return service.get(id, userId, userProgressId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete measurement {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<MeasurementTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll personal measurements for user {}", userId);
        return MeasurementsUtil.getTos(service.getAll(userId));
    }

    public List<MeasurementTo> getAll(int userProgressId) {
        int userId = SecurityUtil.authUserId();
        log.info("user {} requests for all measurements for user {}", userId, userProgressId);
        return MeasurementsUtil.getTos(service.getAll(userId, userProgressId));
    }

    public Measurement create(Measurement measurement) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(measurement);
        log.info("create {} for user {}", measurement, userId);
        return service.create(measurement, userId);
    }

    public void update(Measurement measurement, int id) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.assureIdConsistent(measurement, id);
        log.info("update {} for user {}", measurement, userId);
        service.update(measurement, userId);
    }

    public List<MeasurementTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) for user {}", startDate, endDate, userId);
        return MeasurementsUtil.getTos(service.getBetween(startDate, endDate, userId));
    }

    public List<MeasurementTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userProgressId) {
        int userId = SecurityUtil.authUserId();
        log.info("user {} requests for getBetween dates({} - {}) for user {}", userId, startDate, endDate, userProgressId);
        return MeasurementsUtil.getTos(service.getBetween(startDate, endDate, userId, userProgressId));
    }
}
