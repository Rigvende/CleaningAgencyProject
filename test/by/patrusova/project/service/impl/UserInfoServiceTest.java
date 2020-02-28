package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class UserInfoServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        UserInfoService service = new UserInfoService();
        User user = new User();
        user.setId(95);
        user.setRole("guest");
        user.setName("Irina");
        user.setLastname("Olgich");
        user.setEmail("esme@yandex.by");
        user.setPhone(80291616588L);
        user.setAddress("Borisov");
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = service.doService(user);
        assertNotEquals(expected, actual);
    }
}