package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class RegistrationServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        RegistrationService service = new RegistrationService();
        User user = new User();
        user.setLogin("Esmeralda");
        user.setPassword("Esmeralda");
        user.setRole("guest");
        user.setName("Irina");
        user.setLastname("Olgich");
        user.setEmail("esme@yandex.by");
        user.setPhone(80291616588L);
        user.setAddress("Mogilyov");
        Optional<AbstractEntity> actual = service.doService(user);
        Optional<AbstractEntity> expected = Optional.empty();
        assertNotEquals(actual, expected);
    }
}