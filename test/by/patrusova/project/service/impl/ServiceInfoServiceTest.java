package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.Optional;
import static org.testng.Assert.*;

public class ServiceInfoServiceTest {

    ServiceInfoService infoService = new ServiceInfoService();

    @Test
    public void testDoService() throws ServiceException {
        Service service = new Service();
        service.setId(66);
        service.setService("Укладка каменной плитки");
        service.setCost(BigDecimal.valueOf(120));
        service.setSales(BigDecimal.valueOf(0.00));
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = infoService.doService(service);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testDoServiceAdd() throws ServiceException {
        Service service = new Service();
        service.setService("Посыпка речным песком");
        service.setCost(BigDecimal.valueOf(15));
        service.setSales(BigDecimal.valueOf(0.05));
        Optional<AbstractEntity> expected = Optional.empty();
        Optional<AbstractEntity> actual = infoService.doServiceAdd(service);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testGetService() throws ServiceException {
        Service service = new Service();
        service.setId(40);
        Service actual = infoService.getService(service);
        assertNotNull(actual);
    }
}