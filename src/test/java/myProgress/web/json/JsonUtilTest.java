package myProgress.web.json;

import myProgress.model.Measurement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static myProgress.MeasurementTestData.*;

class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(measurement1);
        System.out.println(json);
        Measurement measurement = JsonUtil.readValue(json, Measurement.class);
        M_MATCHER.assertMatch(measurement, measurement1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(userMeasurements);
        System.out.println(json);
        List<Measurement> measurements = JsonUtil.readValues(json, Measurement.class);
        M_MATCHER.assertMatch(measurements, userMeasurements);
    }
}