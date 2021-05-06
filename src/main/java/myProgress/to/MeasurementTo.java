package myProgress.to;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementTo that = (MeasurementTo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(waist, that.waist) &&
                Objects.equals(hips, that.hips) &&
                Objects.equals(shoulders, that.shoulders) &&
                Objects.equals(quad, that.quad) &&
                Objects.equals(bicep, that.bicep) &&
                Objects.equals(avgCalories, that.avgCalories) &&
                Objects.equals(trainingCount, that.trainingCount) &&
                Objects.equals(avgSteps, that.avgSteps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, weight, waist, hips, shoulders, quad, bicep, avgCalories, trainingCount, avgSteps);
    }
}
