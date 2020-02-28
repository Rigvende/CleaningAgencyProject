package by.patrusova.project.service.impl;

import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.ComplexOrder;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class ShowOrderServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        ClientInfoService clientInfoService = new ClientInfoService();
        ShowOrderService showOrderService = new ShowOrderService();
        String role = "client";
        Client client = new Client();
        client.setIdUser(1);
        client = clientInfoService.getClient(client);
        List<ComplexOrder> actual = showOrderService.doService(role, client);
        assertFalse(actual.isEmpty());
    }
}