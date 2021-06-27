package myProgress.web.user;

import myProgress.MeasurementTestData;
import myProgress.UserTestData;
import myProgress.model.User;
import myProgress.service.UserService;
import myProgress.web.AbstractControllerTest;
import myProgress.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static myProgress.TestUtil.readFromJson;
import static myProgress.UserTestData.*;
import static myProgress.web.user.ProfileRestController.REST_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userService.getAll(), admin);
    }

    @Test
    void update() throws Exception {
        User updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userService.get(USER_ID), updated);
    }

    @Test
    void grantAccess() throws Exception {

        //grant access to admin
        perform(MockMvcRequestBuilders.patch(REST_URL + "/grant_access/" + ADMIN_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        // validate subscriber
        User user = userService.getWithSubscribers(USER_ID);
        assertEquals(Set.of(admin), user.getSubscribers());

        // validate subscription
        User admin = userService.getWithSubscriptions(ADMIN_ID);
        assertEquals(Set.of(user), admin.getSubscriptions());
    }

    @Test
    void getWithMeasurements() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL + "/with-measurements"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));

        User user = readFromJson(action, User.class);

        assertEquals(MeasurementTestData.userMeasurements, user.getMeasurements());
    }

    @Test
    void getWithsubscriptions() throws Exception {

        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL + "/with-subscriptions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        User user = readFromJson(action, User.class);

        assertEquals(Set.of(admin), user.getSubscriptions());

    }

    @Test
    void getWithSubscribers() throws Exception {

        userService.grantAccessToUser(USER_ID, ADMIN_ID);

        ResultActions action = perform(MockMvcRequestBuilders.get(REST_URL + "/with-subscribers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));

        User user = readFromJson(action, User.class);

        assertEquals(Set.of(admin), user.getSubscribers());

    }
}