package by.patrusova.project.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MessageManagerTest {

    @Test
    public void testGetProperty() {
        String expected = "Инфо";
        String actual = MessageManager.getProperty("link.info");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPropertyNegative() {
        String expected = "СПИСОК КЛИНЕРОВ:";
        String actual = MessageManager.getProperty("text.clients");
        assertNotEquals(expected, actual);
    }
}