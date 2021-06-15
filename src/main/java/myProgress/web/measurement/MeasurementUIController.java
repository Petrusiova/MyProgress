package myProgress.web.measurement;

import myProgress.model.Measurement;
import myProgress.to.MeasurementTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = "/measurements", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeasurementUIController extends AbstractMeasurementController {

    @Override
    @GetMapping
    public List<MeasurementTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MeasurementTo> getBetween(@RequestParam @Nullable LocalDate startDate,
                                          @RequestParam @Nullable LocalDate endDate) {
        return super.getBetween(startDate, endDate);
    }

    @Override
    @GetMapping(value = "/{id}")
    public Measurement get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }


//    @PostMapping
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void createOrUpdate(Measurement measurement) {
//        if (measurement.isNew()) {
//            super.create(measurement);
//        } else {
//            super.update(measurement, measurement.getId());
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(
//            @RequestParam Integer id,
                       @RequestParam LocalDate date,
                       @RequestParam Double weight,
                       @RequestParam Double waist,
                       @RequestParam Double hips,
                       @RequestParam @Nullable Double shoulders,
                       @RequestParam @Nullable Double quad,
                       @RequestParam @Nullable Double bicep,
                       @RequestParam @Nullable Integer avgCalories,
                       @RequestParam @Nullable Integer trainingCount,
                       @RequestParam @Nullable Integer avgSteps) {
        super.create(new Measurement(
//                id,
                date, weight, waist, hips, shoulders, quad, bicep, avgCalories, trainingCount, avgSteps));
    }
}
