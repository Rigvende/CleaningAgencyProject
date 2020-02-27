package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.util.Optional;
import static org.testng.Assert.*;

public class BasketServiceTest {

    @Test
    public void testDoService() throws ServiceException {
        Optional<AbstractEntity> actual = Optional.empty();
        BasketPosition position = new BasketPosition();
        position.setIdOrder(6);
        position.setIdService(65);
        BasketService service = new BasketService();
        Optional<AbstractEntity> expected = service.doService(position);
        assertNotEquals(actual, expected);
    }
}