package by.patrusova.project.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConfigurationManagerTest {

    @Test
    public void testGetProperty() {
        String expected = "/jsp/order/placeorderForm.jsp";
        String actual = ConfigurationManager.getProperty("page.placeorder");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPropertyNegative() {
        String expected = "/jsp/order/placeorderForm.jsp";
        String actual = ConfigurationManager.getProperty("page.orderconfirm");
        assertNotEquals(expected, actual);
    }
}