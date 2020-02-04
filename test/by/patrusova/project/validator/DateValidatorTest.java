package by.patrusova.project.validator;

import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.testng.Assert.*;

public class DateValidatorTest {

    @Test
    public void testIsValidOrderDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        assertFalse(DateValidator.isValidOrderDate(date));
    }

    @Test
    public void testIsValidDeadlineDate() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        assertTrue(DateValidator.isValidDeadlineDate(date));
    }
}