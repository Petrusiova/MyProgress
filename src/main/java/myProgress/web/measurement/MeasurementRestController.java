package myProgress.web.measurement;

import myProgress.model.Measurement;
import myProgress.to.MeasurementTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MeasurementRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MeasurementRestController extends AbstractMeasurementController {

    public static final String REST_URL = "/rest/";
    public static final String MEASUREMENTS = "/measurements";

    public static final String PROFILE_URL = "profile" + MEASUREMENTS;
    public static final String USER_PROGRESS_URL = "userProgress/{userProgressId}" + MEASUREMENTS;


    @Override
    @GetMapping(PROFILE_URL + "/{id}")
    public Measurement get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(USER_PROGRESS_URL + "/{id}")
    public Measurement get(@PathVariable int userProgressId, @PathVariable int id) {
        return super.get(id, userProgressId);
    }

    @Override
    @DeleteMapping(PROFILE_URL + "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(PROFILE_URL)
    public List<MeasurementTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(USER_PROGRESS_URL)
    public List<MeasurementTo> getAll(@PathVariable int userProgressId) {
        return super.getAll(userProgressId);
    }

    @Override
    @PutMapping(value = PROFILE_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Measurement measurement, @PathVariable int id) {
        super.update(measurement, id);
    }

    @PostMapping(value = PROFILE_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Measurement> createWithLocation(@RequestBody Measurement measurement) {
        Measurement created = super.create(measurement);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @GetMapping(PROFILE_URL + "/filter")
    public List<MeasurementTo> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        return super.getBetween(startDate, endDate);
    }

    @Override
    @GetMapping(USER_PROGRESS_URL + "/filter")
    public List<MeasurementTo> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate,
            @PathVariable int userProgressId) {
        return super.getBetween(startDate, endDate, userProgressId);
    }
}
