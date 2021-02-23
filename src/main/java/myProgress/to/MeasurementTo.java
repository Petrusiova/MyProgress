package myProgress.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
public class MeasurementTo {

    private final Integer id;
    private final LocalDate date;
    private final Double weight;
    private final Double waist;
    private final Double hips;
    private final Double shoulders;
    private final Double quad;
    private final Double bicep;
    private final Double avgCalories;
    private final Double trainingCount;
    private final Double avgSteps;

}
