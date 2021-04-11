package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    private GuiSettings settings = new GuiSettings();
    private double windowWidth = 1000;
    private double windowHeight = 700;
    private Point windowCoordinates = null;

    @Test
    public void testGetWindowWidth() {
        assertEquals(windowWidth, settings.getWindowWidth());
    }

    @Test
    public void testGetWindowHeight() {
        assertEquals(windowHeight, settings.getWindowHeight());
    }

    @Test
    public void getWindowCoordinates_nullAttribute_returnNull() {
        assertEquals(windowCoordinates, settings.getWindowCoordinates());
    }

    @Test
    public void getWindowCoordinates_nonNullAttribute_returnPointObject() {
        GuiSettings settings = new GuiSettings(1000.0, 700.0, 1, 1);
        Point windowCoordinates = new Point(1, 1);
        assertEquals(windowCoordinates, settings.getWindowCoordinates());
    }

    @Test
    public void equals() {
        GuiSettings firstSettings = new GuiSettings(1000.0, 700.0, 1, 1);
        GuiSettings secondSettings = new GuiSettings(700.0, 1000.0, 2, 2);

        // same object -> returns true
        assertTrue(firstSettings.equals(firstSettings));

        // same values -> returns true
        GuiSettings firstSettingsCopy = new GuiSettings(1000.0, 700.0, 1, 1);
        assertTrue(firstSettings.equals(firstSettingsCopy));

        // different types -> returns false
        assertFalse(firstSettings.equals(1));

        // null -> returns false
        assertFalse(firstSettings.equals(null));

        // different settings -> returns false
        assertFalse(firstSettings.equals(secondSettings));
    }

    @Test
    public void toString_nullWindowCoordinates_success() {
        assertEquals("Width : " + windowWidth + "\n"
                + "Height : " + windowHeight + "\n"
                + "Position : " + windowCoordinates, settings.toString());
    }
}
