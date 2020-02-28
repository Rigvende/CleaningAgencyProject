package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class MailServiceTest {

    MailService service = new MailService();

    @Test
    public void testDoService() throws ServiceException {
        MailService service = new MailService();
        String sendTo = "yulia.elf80lv@gmail.com";
        String subject = "project test";
        String message = "Hello from MailServiceTest";
        boolean actual = service.doService(sendTo, subject, message);
        assertTrue(actual);
    }

    @Test
    public void testTestDoService() throws ServiceException {
        Client client = new Client();
        client.setId(2);
        String expected = "Ольга";
        String actual;
        Optional<AbstractEntity> optional = service.doService(client);
        if (optional.isPresent()) {
            User user = (User) optional.get();
            actual = user.getName();
        } else {
            actual = null;
        }
        assertEquals(expected, actual);
    }
}