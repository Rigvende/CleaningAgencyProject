package by.patrusova.project.validator;

import by.patrusova.project.exception.ServiceException;
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

    @Test
    public void testIsValidDays() {
        boolean actual = NumberValidator.isValidDays("5");
        assertTrue(actual);
    }

    @Test
    public void testIsValidDaysNegative() {
        boolean actual = NumberValidator.isValidDays("-2");
        assertFalse(actual);
    }

    @Test
    public void testIsValidCleanerID() throws ServiceException {
        boolean actual = NumberValidator.isValidCleanerID("3");
        assertTrue(actual);
    }

    @Test
    public void testIsValidCleanerIDNegative() throws ServiceException {
        boolean actual = NumberValidator.isValidCleanerID("2");
        assertFalse(actual);
    }

    @Test
    public void testIsValidClientID() throws ServiceException {
        boolean actual = NumberValidator.isValidClientID("2");
        assertTrue(actual);
    }

    @Test
    public void testIsValidClientIDNegative() throws ServiceException {
        boolean actual = NumberValidator.isValidClientID("1");
        assertFalse(actual);
    }

    @Test
    public void testIsValidUserID() throws ServiceException {
        boolean actual = NumberValidator.isValidUserID("1");
        assertTrue(actual);
    }

    @Test
    public void testIsValidUserIDNegative() throws ServiceException {
        boolean actual = NumberValidator.isValidUserID("596");
        assertFalse(actual);
    }

    @Test
    public void testIsValidOrderID() throws ServiceException {
        boolean actual = NumberValidator.isValidOrderID("6");
        assertTrue(actual);
    }

    @Test
    public void testIsValidOrderIDNegative() throws ServiceException {
        boolean actual = NumberValidator.isValidOrderID("1");
        assertFalse(actual);
    }

    @Test
    public void testIsValidServiceID() throws ServiceException {
        boolean actual = NumberValidator.isValidServiceID("40");
        assertTrue(actual);
    }

    @Test
    public void testIsValidServiceIDNegative() throws ServiceException {
        boolean actual = NumberValidator.isValidServiceID("1");
        assertFalse(actual);
    }

    @Test
    public void testIsValidBasketID() throws ServiceException {
        boolean actual = NumberValidator.isValidBasketID("1");
        assertTrue(actual);
    }

    @Test
    public void testIsValidBasketIDNegative() throws ServiceException {
        boolean actual = NumberValidator.isValidBasketID("1080");
        assertFalse(actual);
    }

    @Test
    public void testIsValidID() {
        boolean actual = NumberValidator.isValidID("9");
        assertTrue(actual);
    }

    @Test
    public void testIsValidIDNegative() {
        boolean actual = NumberValidator.isValidID("f");
        assertFalse(actual);
    }
}