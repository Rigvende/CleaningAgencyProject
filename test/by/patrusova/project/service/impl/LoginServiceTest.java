package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class LoginServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        LoginService service = new LoginService();
        User user = new User();
        user.setLogin("dobby");
        user.setPassword("dobbi");
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = service.doService(user);
        assertEquals(actual, expected);
    }
}