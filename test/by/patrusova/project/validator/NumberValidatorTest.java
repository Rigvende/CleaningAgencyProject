package by.patrusova.project.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberValidatorTest {

    @Test
    public void testIsValidDecimal() {
        boolean actual = NumberValidator.isValidDecimal("0.15");
        assertTrue(actual);
    }

    @Test
    public void testIsValidDecimalNegative() {
        boolean actual = NumberValidator.isValidDecimal("0.dd");
        assertFalse(actual);
    }

    @Test
    public void testIsValidCost() {
        boolean actual = NumberValidator.isValidCost("99.9");
        assertTrue(actual);
    }

    @Test
    public void testIsValidCostNegative() {
        boolean actual = NumberValidator.isValidCost("999.a");
        assertFalse(actual);
    }

    @Test
    public void testTestIsValidMark() {
        boolean actual = NumberValidator.isValidCost("9");
        assertTrue(actual);
    }

    @Test
    public void testTestIsValidMarkNegative() {
        boolean actual = NumberValidator.isValidCost("");
        assertFalse(actual);
    }
}