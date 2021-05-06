package myProgress.web.measurement;

import myProgress.MeasurementTestData;
import myProgress.model.Measurement;
import myProgress.service.MeasurementService;
import myProgress.util.exception.NotFoundException;
import myProgress.web.AbstractControllerTest;
import myProgress.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static myProgress.MeasurementTestData.*;
import static myProgress.TestUtil.readFromJson;
import static myProgress.UserTestData.USER_ID;
import static myProgress.UserTestData.admin;
import static myProgress.util.MeasurementsUtil.createTo;
import static myProgress.util.MeasurementsUtil.getTos;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MeasurementRestControllerTest extends AbstractControllerTest {

    private static final String PROFILE_URL = MeasurementRestController.REST_URL + MeasurementRestController.PROFILE_URL + '/';
    private static final String USER_PROGRESS_URL = MeasurementRestController.REST_URL + MeasurementRestController.USER_PROGRESS_URL;

    @Autowired
    private MeasurementService measurementService;

    @Test
    void getMyMeasurement() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_URL + M_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_MATCHER.contentJson(measurement1));
    }

    @Test
    void getUserMeasurement() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_PROGRESS_URL + "/" + M_ADMIN_ID, admin.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_MATCHER.contentJson(measurement4));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(PROFILE_URL + M_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> measurementService.get(M_ID, USER_ID));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_MATCHER.contentJson(userMeasurements));
    }

    @Test
    void testGetAll() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_PROGRESS_URL, admin.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_TO_MATCHER.contentJson(getTos(List.of(measurement4))));
    }

    @Test
    void update() throws Exception {
        Measurement updated = MeasurementTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(PROFILE_URL + M_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        M_MATCHER.assertMatch(measurementService.get(M_ID, USER_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Measurement newMeasurement = MeasurementTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(PROFILE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeasurement)))
                .andExpect(status().isCreated());

        Measurement created = readFromJson(action, Measurement.class);
        int newId = created.id();
        newMeasurement.setId(newId);
        M_MATCHER.assertMatch(created, newMeasurement);
        M_MATCHER.assertMatch(measurementService.get(newId, USER_ID), newMeasurement);
    }

    @Test
    void getBetweenAllMine() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_URL + "filter?startDate=&endDate="))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_MATCHER.contentJson(userMeasurements));
    }

    @Test
    void getBetweenFilterMine() throws Exception {
        perform(MockMvcRequestBuilders.get(PROFILE_URL + "filter")
                .param("startDate", "2021-03-09").param("endDate", "2021-03-09"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_TO_MATCHER.contentJson(getTos(List.of(measurement1))));
    }

    @Test
    void getBetweenFilterUsers() throws Exception {
        perform(MockMvcRequestBuilders.get(USER_PROGRESS_URL + "/filter", admin.getId())
                .param("startDate", "2021-02-22"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(M_TO_MATCHER.contentJson(List.of(createTo(measurement4))));
    }
}