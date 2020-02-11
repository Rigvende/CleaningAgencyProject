package by.patrusova.project.validator;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class DateValidatorTest {

    @Test
    public void testIsValidOrderDate() {
        LocalDate date = LocalDate.now();
        boolean expected = DateValidator.isValidOrderDate(date);
        assertTrue(expected);
    }
    @Test
    public void testIsValidOrderDateNegative() {
        LocalDate date = LocalDate.of(2020,02, 12);
        boolean expected = DateValidator.isValidOrderDate(date);
        assertFalse(expected);
    }

    @Test
    public void testIsValidDeadlineDate() {
        LocalDate date = LocalDate.now();
        boolean expected = DateValidator.isValidDeadlineDate(date);
        assertTrue(expected);
    }
    @Test
    public void testIsValidDeadlineDateNegative() {
        LocalDate date = LocalDate.of(2020,02,10);
        boolean expected = DateValidator.isValidDeadlineDate(date);
        assertFalse(expected);
    }
}