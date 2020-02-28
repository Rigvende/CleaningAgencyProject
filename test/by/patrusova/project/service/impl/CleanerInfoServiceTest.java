package by.patrusova.project.service.impl;

import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.ServiceException;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.Optional;
import static org.testng.Assert.*;

public class CleanerInfoServiceTest {

    private CleanerInfoService service = new CleanerInfoService();

    @Test
    public void testDoService() throws ServiceException {
        Cleaner cleaner = new Cleaner();
        cleaner.setCommission(BigDecimal.valueOf(0.15));
        cleaner.setNotes("часто опаздывает");
        cleaner.setIdUser(94);
        cleaner.setId(20);
        Optional<AbstractEntity> actual = service.doService(cleaner);
        assertEquals(actual, Optional.of(cleaner));
    }

    @Test
    public void testGetCleaner() throws ServiceException {
        Cleaner cleaner = new Cleaner();
        cleaner.setIdUser(57);
        long actual = service.getCleaner(cleaner).getId();
        long expected = 10L;
        assertEquals(expected, actual);
    }
}