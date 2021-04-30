package myProgress.web;

import myProgress.MeasurementTestData;
import myProgress.UserTestData;
import org.junit.Test;

import static myProgress.MeasurementTestData.M_ID;
import static myProgress.model.AbstractBaseEntity.START_SEQ;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(UserTestData.user.getName()))
                        )
                )));
    }

    @Test
    public void getMeasurements() throws Exception {
        perform(get("/measurements"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("measurements"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/measurements.jsp"))
                .andExpect(model().attribute("measurements", hasSize(3)))
                .andExpect(model().attribute("measurements", hasItem(
                        allOf(
                                hasProperty("id", is(M_ID)),
                                hasProperty("weight", is(MeasurementTestData.measurement1.getWeight()))
                        )
                )));
    }

    @Test
    public void getRoot() throws Exception {
        perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }
}