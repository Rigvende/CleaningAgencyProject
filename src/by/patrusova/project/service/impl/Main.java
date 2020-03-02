package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.ComplexPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ServiceException, DaoException {
        ShowService showService = new ShowService();
        Order order = new Order();
        order.setId(115);
        order.setOrderStatus("new");
        order.setIdClient(2);
        List<ComplexPosition> positions = showService.doService(order.getId());
        System.out.println(positions);
        DeleteEntityService service = new DeleteEntityService();
        BasketPosition position = new BasketPosition();
        position.setIdOrder(115);
        position.setIdService(60);
        position.setId(130);
        service.doService(position);
        positions = showService.doService(order.getId());
        System.out.println(positions);


//        DeleteEntityService service = new DeleteEntityService();
//        BasketDao basketDao = DaoFactory.createBasketDao();
//        Order order = new Order();
//        order.setId(87);
//        System.out.println(basketDao.deleteAllByOrderId(order.getId()));
//        System.out.println(service.doService(order));
    }
}
