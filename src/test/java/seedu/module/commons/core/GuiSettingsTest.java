package seedu.module.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    private static GuiSettings standardSettings = new GuiSettings();

    @Test
    public void equals() {
        //EP: Same object
        assertTrue(standardSettings.equals(standardSettings));

        //EP: Different object, same values
        GuiSettings sameSettings = new GuiSettings();
        assertTrue(standardSettings.equals(sameSettings));

        //EP: Different object, different values
        GuiSettings differentSettings = new GuiSettings(900, 1500, 50, 60);
        assertFalse(standardSettings.equals(differentSettings));

        //EP: Not GuiSettings
        assertFalse(standardSettings.equals("Not GuiSettings object"));
    }

    @Test
    public void getters() {
        GuiSettings guiSettings = new GuiSettings(500, 600, 50, 60);
        assertEquals(guiSettings.getWindowWidth(), 500);
        assertEquals(guiSettings.getWindowHeight(), 600);
        assertEquals(guiSettings.getWindowCoordinates(), new Point(50, 60));

        //null Point
        assertNull(standardSettings.getWindowCoordinates());
    }
}
