package myProgress.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Getter
@ToString
public class Measurement extends AbstractBaseEntity {

    private LocalDate date = LocalDate.now();
    @NonNull
    private final Double weight;
    @NonNull
    private final Double waist;
    @NonNull
    private final Double hips;
    

    /**
     * Nullable non-required measurements
     **/
    private final Double shoulders;
    private final Double quad;
    private final Double bicep;
    private final Integer avgCalories;
    private final Integer trainingCount;
    private final Integer avgSteps;

    public Measurement(Integer id, LocalDate date, Double weight, Double waist,
                       Double hips, Double shoulders, Double quad, Double bicep,
                       Integer avgCalories, Integer trainingCount, Integer avgSteps) {
        super(id);
        this.date = date;
        this.weight = weight;
        this.waist = waist;
        this.hips = hips;
        this.shoulders = shoulders;
        this.quad = quad;
        this.bicep = bicep;
        this.avgCalories = avgCalories;
        this.trainingCount = trainingCount;
        this.avgSteps = avgSteps;
    }

    public Measurement(Integer id, Double weight, Double waist,
                       Double hips, Double shoulders, Double quad, Double bicep,
                       Integer avgCalories, Integer trainingCount, Integer avgSteps) {
        super(id);
        this.weight = weight;
        this.waist = waist;
        this.hips = hips;
        this.shoulders = shoulders;
        this.quad = quad;
        this.bicep = bicep;
        this.avgCalories = avgCalories;
        this.trainingCount = trainingCount;
        this.avgSteps = avgSteps;
    }

    public Measurement(LocalDate date, Double weight, Double waist, Double hips,
                       Double shoulders, Double quad, Double bicep, Integer avgCalories,
                       Integer trainingCount, Integer avgSteps) {
        this(null, date, weight, waist, hips, shoulders, quad, bicep, avgCalories, trainingCount, avgSteps);
    }

    public Measurement(LocalDate date, Double weight, Double waist, Double hips) {
        this(null, date, weight, waist, hips, null, null, null, null, null, null);
    }
}

