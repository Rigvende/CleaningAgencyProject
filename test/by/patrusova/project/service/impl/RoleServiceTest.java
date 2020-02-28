package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class RoleServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        RoleService service = new RoleService();
        long id = 2;
        String role = "admin";
        String actual;
        Optional<AbstractEntity> optional = service.doService(id, role);
        if (optional.isPresent()) {
            User user = (User) optional.get();
            actual = user.getName();
        } else {
            actual = null;
        }
       assertNull(actual);
    }
}