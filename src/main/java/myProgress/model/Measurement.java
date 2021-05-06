package myProgress.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "measurements",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"},
                name = "measurements_unique_user_date_idx")})
public class Measurement extends AbstractBaseEntity {

    @Column(name = "date")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    @Column(name = "weight")
    @NotNull
    private Double weight;

    @Column(name = "waist")
    @NotNull
    private Double waist;

    @Column(name = "hips")
    @NotNull
    private Double hips;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private User user;

    /**
     * Nullable non-required measurements
     **/
    @Column(name = "shoulders")
    private Double shoulders;

    @Column(name = "quad")
    private Double quad;

    @Column(name = "bicep")
    private Double bicep;

    @Column(name = "avgCalories")
    private Integer avgCalories;

    @Column(name = "trainingCount")
    private Integer trainingCount;

    @Column(name = "avgSteps")
    private Integer avgSteps;

    public Measurement(Integer id, LocalDate date, Double weight, Double waist, Double hips) {
        this(id, date, weight, waist, hips, null, null, null, null, null, null);
    }

    public Measurement(LocalDate date, Double weight, Double waist, Double hips) {
        this(null, date, weight, waist, hips, null, null, null, null, null, null);
    }

    public Measurement(Integer id, LocalDate date,  Double weight, Double waist,
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
}

