package by.patrusova.project.validator;

import by.patrusova.project.exception.DaoException;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class RegistrationDataValidatorTest {

    @Test
    public void testIsValidLogin() throws DaoException {
        boolean actual = RegistrationDataValidator.isValidLogin("qwerty");
        assertTrue(actual);
    }
    @Test
    public void testIsValidLoginNegative() throws DaoException {
        boolean actual = RegistrationDataValidator.isValidLogin("qwek");
        assertFalse(actual);
    }

    @Test
    public void testIsValidPassword() {
        boolean actual = RegistrationDataValidator.isValidPassword("qwerty");
        assertTrue(actual);
    }
    @Test
    public void testIsValidPasswordNegative() {
        boolean actual = RegistrationDataValidator.isValidPassword("jhffgjh=");
        assertFalse(actual);
    }

    @Test
    public void testIsPasswordRepeated() {
        boolean actual = RegistrationDataValidator.isPasswordRepeated("qwerty", "qwerty");
        assertTrue(actual);
    }
    @Test
    public void testIsPasswordRepeatedNegative() {
        boolean actual = RegistrationDataValidator.isPasswordRepeated("qwerty", "qazwsx");
        assertFalse(actual);
    }

    @Test
    public void testIsValidPhone() {
        boolean actual = RegistrationDataValidator.isValidPhone("80291616573");
        assertTrue(actual);
    }
    @Test
    public void testIsValidPhoneNegative() {
        boolean actual = RegistrationDataValidator.isValidPhone("80k29");
        assertFalse(actual);
    }

    @Test
    public void testIsValidEmail() {
        boolean actual = RegistrationDataValidator.isValidEmail("blinov@gmail.com");
        assertTrue(actual);
    }
    @Test
    public void testIsValidEmailNegative() {
        boolean actual = RegistrationDataValidator.isValidEmail("blinov");
        assertFalse(actual);
    }

    @Test
    public void testIsValidName() {
        boolean actual = RegistrationDataValidator.isValidName("Ольга");
        assertTrue(actual);
    }

    @Test
    public void testIsValidNameNegative() {
        boolean actual = RegistrationDataValidator.isValidName("blinov");
        assertFalse(actual);
    }

    @Test
    public void testIsValidLastname() {
        boolean actual = RegistrationDataValidator.isValidLastname("Петров-Водкин");
        assertTrue(actual);
    }

    @Test
    public void testIsValidLastnameNegative() {
        boolean actual = RegistrationDataValidator.isValidLastname("12345");
        assertFalse(actual);
    }
}