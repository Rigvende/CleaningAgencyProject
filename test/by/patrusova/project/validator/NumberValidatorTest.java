package by.patrusova.project.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberValidatorTest {

    @Test
    public void testIsValidDecimal() {
        boolean actual = NumberValidator.isValidDecimal("commission", 0.15);
        assertTrue(actual);
    }
    @Test
    public void testIsValidDecimalNegative() {
        boolean actual = NumberValidator.isValidDecimal("cost", 1000.15);
        assertFalse(actual);
    }

    @Test
    public void testIsValidMark() {
        boolean actual = NumberValidator.isValidMark(7);
        assertTrue(actual);
    }
    @Test
    public void testIsValidMarkNegative() {
        boolean actual = NumberValidator.isValidMark(10);
        assertFalse(actual);
    }

    @Test
    public void testSecondIsValidDecimal() {
        boolean actual = NumberValidator.isValidDecimal("0.15");
        assertTrue(actual);
    }

    @Test
    public void testSecondIsValidDecimalNegative() {
        boolean actual = NumberValidator.isValidDecimal("0.dd");
        assertFalse(actual);
    }
}