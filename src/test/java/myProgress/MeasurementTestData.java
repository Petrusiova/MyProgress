package myProgress;

import myProgress.model.Measurement;
import myProgress.model.Role;
import myProgress.model.User;
import myProgress.to.MeasurementTo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static myProgress.UserTestData.ADMIN_ID;
import static myProgress.UserTestData.USER_ID;
import static myProgress.model.AbstractBaseEntity.START_SEQ;

public class MeasurementTestData {
    public static final TestMatcher<Measurement> M_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(Measurement.class, "user");

    public static final TestMatcher<MeasurementTo> M_TO_MATCHER =
            TestMatcher.usingIgnoringFieldsComparator(MeasurementTo.class);

    public static final int M_ID = START_SEQ + 5;
    public static final int M_ADMIN_ID = M_ID + 3;

    public static final Measurement measurement1 = new Measurement(M_ID, LocalDate.of(2021, 3, 9), 48.5, 58d, 86d);
    public static final Measurement measurement2 = new Measurement(M_ID + 1, LocalDate.of(2021, 3, 1), 48.1, 58d, 86d);
    public static final Measurement measurement3 = new Measurement(M_ID + 2, LocalDate.of(2021, 2, 22), 48.0, 58d, 86d);
    public static final Measurement measurement4 = new Measurement(M_ADMIN_ID, LocalDate.of(2021, 2, 22), 48.0, 58d, 86d);

    public static final List<Measurement> userMeasurements = List.of(measurement1, measurement2, measurement3);

    public static Measurement getNew() {
        return new Measurement(null, LocalDate.now(), 48.8d, 59d, 86d);
    }

    public static Measurement getUpdated() {
        return new Measurement(M_ID,
                measurement1.getDate().minusDays(1),
                48d,
                measurement1.getWaist() + 1,
                measurement1.getHips() + 1);
    }
}
