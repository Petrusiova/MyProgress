package myProgress.util;


import myProgress.model.Measurement;
import myProgress.to.MeasurementTo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MeasurementsUtil {

    public static final List<Measurement> measurements = Arrays.asList(
            new Measurement(LocalDate.now().minusDays(5), 48.4, 58d, 86d),
            new Measurement(LocalDate.now().minusDays(4), 48.3, 58d, 86d),
            new Measurement(LocalDate.now().minusDays(3), 48.2, 58d, 86d),
            new Measurement(LocalDate.now().minusDays(2), 48.1, 58d, 86d),
            new Measurement(LocalDate.now().minusDays(1), 48.0, 58d, 86d)
    );

    public static List<MeasurementTo> getTos(Collection<Measurement> measurements) {
//        return measurements == null ? List.of() : measurements
//                .stream()
//                .map(MeasurementsUtil::createTo)
//                .collect(Collectors.toList());

        return
//                measurements == null ? List.of() :
                measurements
                .stream()
                .map(MeasurementsUtil::createTo)
                .collect(Collectors.toList());

    }

    private static MeasurementTo createTo(Measurement measurement) {
        return new MeasurementTo(
                measurement.getId(), 
                measurement.getDate(),
                measurement.getWeight(),
                measurement.getWaist(),
                measurement.getHips(),
                measurement.getShoulders(),
                measurement.getQuad(),
                measurement.getBicep(),
                measurement.getAvgCalories(),
                measurement.getTrainingCount(),
                measurement.getAvgSteps());
    }
}
