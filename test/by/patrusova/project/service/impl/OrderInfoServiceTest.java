package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.testng.Assert.*;

public class OrderInfoServiceTest {

    private OrderInfoService service = new OrderInfoService();

    @Test
    public void testDoService() throws ServiceException {
        Order order = new Order();
        order.setId(52);
        order.setOrderStatus("registered");
        order.setIdCleaner(20);
        Optional<AbstractEntity> optional = service.doService(order);
        Order actual = (Order) optional.get();
        assertNotNull(actual);
    }

    @Test
    public void testTestDoService() throws ServiceException {
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = service.doService(52, 2, 9);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testTestDoService1() throws ServiceException {
        double expected = 15.0;
        double actual = service.doService(7).doubleValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetOrder() throws ServiceException {
        Order order = new Order();
        order.setId(6);
        long expected = 8;
        long actual = service.getOrder(order).getIdClient();
        assertEquals(expected, actual);
    }

    @Test
    public void testPlaceOrder() throws DaoException, ServiceException {
        Order order = new Order();
        order.setOrderTime(LocalDate.of(2020, 2, 28));
        order.setDeadline(LocalDate.of(2020, 3, 18));
        order.setOrderStatus("new");
        order.setId(0);
        order.setIdClient(39);
        OrderDao dao = DaoFactory.createOrderDao();
        dao.create(order);
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = service.placeOrder(order);
        assertNotEquals(expected, actual);
    }
}