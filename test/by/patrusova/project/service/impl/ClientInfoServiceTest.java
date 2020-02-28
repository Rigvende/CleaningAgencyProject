package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.Optional;
import static org.testng.Assert.*;

public class ClientInfoServiceTest {

    private ClientInfoService service = new ClientInfoService();

    @Test
    public void testDoService() throws ServiceException {
        Client client = new Client();
        client.setIdUser(61);
        client.setLocation("---");
        client.setRelative("Иванов Олег Васильевич");
        Optional<AbstractEntity> actual = service.doService(client);
        assertEquals(actual, Optional.of(client));
    }

    @Test
    public void testDoServiceByAdmin() throws ServiceException {
        Client client = new Client();
        client.setIdUser(61);
        client.setDiscount(BigDecimal.valueOf(0.1));
        client.setNotes("своя краска");
        client.setId(28);
        Optional<AbstractEntity> actual = service.doServiceByAdmin(client);
        assertNotEquals(actual, Optional.empty());
    }

    @Test
    public void testTestDoService() throws ServiceException {
        Client client = new Client();
        client.setIdUser(67);
        client.setNotes("пенсионер, своя краска");
        Optional<AbstractEntity> actual =
                service.doService(67, 20, "пенсионер, своя краска");
        assertNotEquals(actual, Optional.of(client));
    }

    @Test
    public void testGetClient() throws ServiceException {
        Client client = new Client();
        client.setIdUser(61);
        String expected = service.getClient(client).getNotes();
        assertNotEquals(null, expected);
    }
}