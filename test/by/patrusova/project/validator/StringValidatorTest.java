package by.patrusova.project.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class StringValidatorTest {


    @DataProvider
    public Object[][] checkingData() {
        return new Object[][]{
                {true, StringValidator.isValidStringSize("notes", "adjfdsjfla")},
                {true, StringValidator.isValidStringSize("email", "adjfdsjfla")},
                {true, StringValidator.isValidStringSize("location", "adjfdfdgggggsfafafdsjfla")},
                {true, StringValidator.isValidStringSize("address", "adjfdfdsfggggggafafdsjfla")},
                {true, StringValidator.isValidStringSize("relative", "adjfdfdgggggsfafafdsjfla")},
                {true, StringValidator.isValidStringSize("service", "adjfdf")},
                {true, StringValidator.isValidStringSize("name", "ad")},
                {true, StringValidator.isValidStringSize("lastname", "a")},
        };
    }
    @Test(dataProvider = "checkingData", dataProviderClass = StringValidatorTest.class)
    public void testIsValidStringSize(boolean b, boolean expected) {
        final boolean actual = true;
        assertEquals(actual, expected);
    }

    @DataProvider
    public Object[][] checkingFalseData() {
        return new Object[][]{
                {false, StringValidator.isValidStringSize("email", "a")},
                {false, StringValidator.isValidStringSize("notes",
                        "adjfdsjflaffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" +
                                "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")},
                {false, StringValidator.isValidStringSize("location", "adjfdfdggg")},
                {false, StringValidator.isValidStringSize("address", "adjfd")},
                {false, StringValidator.isValidStringSize("relative", "a")},
                {false, StringValidator.isValidStringSize("service",
                        "adjfkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkdf")},
                {false, StringValidator.isValidStringSize("name", "adhhhhhhhhhhhhhhhhhhhhhhhh")},
                {false, StringValidator.isValidStringSize("lastname", "")},
        };
    }
    @Test(dataProvider = "checkingFalseData", dataProviderClass = StringValidatorTest.class)
    public void testIsValidStringSizeFalse(boolean b, boolean expected) {
        final boolean actual = true;
        assertNotEquals(actual, expected);
    }

    @Test
    public void testIsValidRole() {
        String actual = "admin";
        boolean expected = StringValidator.isValidRole(actual);
        assertTrue(expected);
    }
    @Test
    public void testIsValidRoleNegative() {
        String actual = "mimokrokodil";
        boolean expected = StringValidator.isValidRole(actual);
        assertFalse(expected);
    }

    @Test
    public void testIsValidStatus() {
        String actual = "registered";
        boolean expected = StringValidator.isValidStatus(actual);
        assertTrue(expected);
    }
    @Test
    public void testIsValidStatusNegative() {
        String actual = "failed";
        boolean expected = StringValidator.isValidStatus(actual);
        assertFalse(expected);
    }
}