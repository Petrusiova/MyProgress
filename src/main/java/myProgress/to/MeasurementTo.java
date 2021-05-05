package myProgress.to;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
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
