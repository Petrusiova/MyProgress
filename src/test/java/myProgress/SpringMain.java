package myProgress;

import myProgress.model.Role;
import myProgress.model.User;
import myProgress.to.MeasurementTo;
import myProgress.web.measurement.MeasurementRestController;
import myProgress.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "classpath:spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            User user = adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            adminUserController.getAccessToUser(100001);
            User igor = adminUserController.create(new User(null, "igor", "igor@mail.ru", "password", Role.ADMIN));

            System.out.println();

            MeasurementRestController measurementRestController = appCtx.getBean(MeasurementRestController.class);

            measurementRestController.getAll();
            measurementRestController.getAll(user.getId());

            List<MeasurementTo> list =
                    measurementRestController.getBetween(
                            LocalDate.now().minusDays(5),
                            LocalDate.now().minusDays(4));
            list.forEach(System.out::println);
            measurementRestController.getAll(8);
            System.out.println();
            System.out.println(measurementRestController.getBetween(null, null));
        }
    }
}
