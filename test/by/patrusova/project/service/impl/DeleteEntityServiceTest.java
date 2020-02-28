package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class DeleteEntityServiceTest {

    DeleteEntityService service = new DeleteEntityService();

    @Test
    public void testDoService() throws DaoException, ServiceException {
        OrderDao orderDao = DaoFactory.createOrderDao();
        Order order = new Order();
        order.setIdClient(21);
        orderDao.createNew(order);
        order.setId(orderDao.findNew(21));
        BasketDao basketDao = DaoFactory.createBasketDao();
        BasketPosition position = new BasketPosition();
        position.setIdService(45);
        position.setIdOrder(order.getId());
        basketDao.create(position);
        Optional<AbstractEntity> actual = Optional.empty();
        Optional<AbstractEntity> expected = service.doService(order);
        assertEquals(actual, expected);
    }

    @Test
    public void testTestDoService() throws DaoException, ServiceException {
        OrderDao dao = DaoFactory.createOrderDao();
        Order order = new Order();
        order.setIdClient(21);
        dao.createNew(order);
        boolean expected = service.doService(21);
        assertTrue(expected);
    }
}