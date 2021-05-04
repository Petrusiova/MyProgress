package myProgress.web;

import myProgress.model.Measurement;
import myProgress.model.User;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static myProgress.MeasurementTestData.M_MATCHER;
import static myProgress.MeasurementTestData.userMeasurements;
import static myProgress.UserTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, admin, user);
                            }
                        }
                ));
    }

    @Test
    void getMeasurements() throws Exception {
        perform(get("/measurements"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("measurements"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/measurements.jsp"))
                .andExpect(model().attribute("measurements",
                        new AssertionMatcher<List<Measurement>>() {
                            @Override
                            public void assertion(List<Measurement> actual) throws AssertionError {
                                M_MATCHER.assertMatch(actual, userMeasurements);
                            }
                        }
                ));
    }

    @Test
    void getRoot() throws Exception {
        perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }
}