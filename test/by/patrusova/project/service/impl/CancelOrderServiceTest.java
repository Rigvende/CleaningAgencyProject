package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.assertEquals;

public class CancelOrderServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        Order order = new Order();
        order.setIdClient(2);
        order.setOrderStatus("registered");
        CancelOrderService service = new CancelOrderService();
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = service.doService(order);
        assertEquals(expected, actual);
    }
}