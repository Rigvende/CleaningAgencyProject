package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.ComplexPosition;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;

public class ShowServiceTest {

    private ShowService showService = new ShowService();

    @Test
    public void testDoService() throws ServiceException {
        String condition = "admin";
        List<AbstractEntity> actual = showService.doService(condition);
        assertFalse(actual.isEmpty());
    }

    @Test
    public void testTestDoService() throws ServiceException {
        List<ComplexPosition> actual = showService.doService(6);
        assertFalse(actual.isEmpty());
    }
}