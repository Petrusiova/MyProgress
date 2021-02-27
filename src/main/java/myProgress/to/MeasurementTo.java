package myProgress.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class MeasurementTo {

    private Integer id;
    private LocalDate date;
    private Double weight;
    private Double waist;
    private Double hips;
    private Double shoulders;
    private Double quad;
    private Double bicep;
    private Integer avgCalories;
    private Integer trainingCount;
    private Integer avgSteps;

}
