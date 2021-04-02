package seedu.smartlib.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.GuiSettings;

public class UserPrefsTest {

    private final GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
    private final GuiSettings guiSettings2 = new GuiSettings(1, 1, 1, 1);
    private final Path filePath = Paths.get("smartLib/file/path");
    private final Path filePath2 = Paths.get("smartLib/files/path");

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs up = new UserPrefs();
        assertThrows(NullPointerException.class, () -> up.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings() {
        UserPrefs up = new UserPrefs();
        assertDoesNotThrow(() -> up.setGuiSettings(guiSettings));
        assertDoesNotThrow(() -> up.setGuiSettings(guiSettings2));
    }

    @Test
    public void setSmartLibFilePath_nullPath_throwsNullPointerException() {
        UserPrefs up = new UserPrefs();
        assertThrows(NullPointerException.class, () -> up.setSmartLibFilePath(null));
    }

    @Test
    public void setSmartLibFilePath_validPath() {
        UserPrefs up = new UserPrefs();
        assertDoesNotThrow(() -> up.setSmartLibFilePath(filePath));
        assertDoesNotThrow(() -> up.setSmartLibFilePath(filePath2));
    }

    @Test
    public void getGuiSettings() {
        UserPrefs up = new UserPrefs();
        UserPrefs upCopy = new UserPrefs();
        UserPrefs up2 = new UserPrefs();
        up.setGuiSettings(guiSettings);
        upCopy.setGuiSettings(guiSettings);

        // EP: same guisettings
        assertEquals(up.getGuiSettings(), upCopy.getGuiSettings());

        // EP: second userpref missing guisettings
        assertNotEquals(up.getGuiSettings(), up2.getGuiSettings());

        // EP: different guisettings
        upCopy.setGuiSettings(guiSettings2);
        assertNotEquals(up.getGuiSettings(), up2.getGuiSettings());
    }

    @Test
    public void getSmartLibFilePath() {
        UserPrefs up = new UserPrefs();
        UserPrefs upCopy = new UserPrefs();
        UserPrefs up2 = new UserPrefs();
        up.setSmartLibFilePath(filePath);
        upCopy.setSmartLibFilePath(filePath);

        // EP: same filepath
        assertEquals(up.getSmartLibFilePath(), upCopy.getSmartLibFilePath());

        // EP: second userpref missing filepath
        assertNotEquals(up.getSmartLibFilePath(), up2.getSmartLibFilePath());

        // EP: different filepath
        up2.setSmartLibFilePath(filePath2);
        assertNotEquals(up.getSmartLibFilePath(), up2.getSmartLibFilePath());
    }

    @Test
    public void equals() {
        UserPrefs up = new UserPrefs();
        UserPrefs upCopy = new UserPrefs(up);

        // EP: same object
        assertTrue(up.equals(up));

        // EP: null
        assertFalse(up.equals(null));

        // EP: different data types
        assertFalse(up.equals(5.0f));
        assertFalse(up.equals(" "));

        // EP: both with empty values
        assertTrue(up.equals(upCopy));

        // EP: both with same values
        up.setGuiSettings(guiSettings);
        up.setSmartLibFilePath(filePath);
        upCopy.setGuiSettings(guiSettings);
        upCopy.setSmartLibFilePath(filePath);
        assertTrue(up.equals(upCopy));

        // EP: different guisettings and filepath
        upCopy.setGuiSettings(guiSettings2);
        upCopy.setSmartLibFilePath(filePath2);
        assertFalse(up.equals(upCopy));

        // EP: different guisettings but same filepath
        upCopy.setSmartLibFilePath(filePath);
        assertFalse(up.equals(upCopy));

        // EP: same guisettings but different filepath
        upCopy.setGuiSettings(guiSettings);
        upCopy.setSmartLibFilePath(filePath2);
        assertFalse(up.equals(upCopy));
    }

    @Test
    public void constructorWithoutParams() {
        // empty constructor: both objects treated as equal
        UserPrefs up = new UserPrefs();
        UserPrefs up2 = new UserPrefs();
        assertEquals(up, up2);
    }

    @Test
    public void constructorWithParams() {
        // constructor with params: equal if they are copies of each other
        UserPrefs up = new UserPrefs();
        up.setGuiSettings(guiSettings);
        up.setSmartLibFilePath(filePath);
        UserPrefs up2 = new UserPrefs(up);

        UserPrefs up3 = new UserPrefs(up);
        up3.setGuiSettings(guiSettings2);
        up3.setSmartLibFilePath(filePath2);
        UserPrefs up4 = new UserPrefs(up3);

        // EP: copies of each other
        assertEquals(up, up2);
        assertEquals(up3, up4);

        // EP: not copies of each other
        assertNotEquals(up, up3);
        assertNotEquals(up, up4);
        assertNotEquals(up2, up3);
        assertNotEquals(up2, up4);
    }

    @Test
    public void resetData() {
        UserPrefs up = new UserPrefs();
        up.setGuiSettings(guiSettings);
        up.setSmartLibFilePath(filePath);
        up.resetData(new UserPrefs());
        assertEquals(up, new UserPrefs());
    }

    @Test
    public void hashCodeTest() {
        UserPrefs up = new UserPrefs();
        up.setGuiSettings(guiSettings);
        up.setSmartLibFilePath(filePath);
        UserPrefs up2 = new UserPrefs(up);
        int hashcode = up.hashCode();

        // same object, same hashcode
        assertEquals(hashcode, up.hashCode());

        // different object, same guisettings and filepath -> same hashcode
        assertEquals(hashcode, up2.hashCode());

        // different object, different guisettings and filepath -> different hashcode
        up2.setGuiSettings(guiSettings2);
        up2.setSmartLibFilePath(filePath2);
        assertNotEquals(hashcode, up2.hashCode());

        // different object, same filepath but different guisettings -> different hashcode
        up2.setSmartLibFilePath(filePath);
        assertNotEquals(hashcode, up2.hashCode());

        // different object, same guisettings but different filepath -> different hashcode
        up2.setGuiSettings(guiSettings);
        up2.setSmartLibFilePath(filePath2);
        assertNotEquals(hashcode, up2.hashCode());
    }

    @Test
    public void toStringTest() {
        UserPrefs up = new UserPrefs();
        up.setGuiSettings(guiSettings);
        up.setSmartLibFilePath(filePath);
        UserPrefs up2 = new UserPrefs(up);
        String string = up.toString();

        // same object, same string representation
        assertEquals(string, up.toString());

        // different object, same guisettings and filepath -> same string
        assertEquals(string, up2.toString());

        // different object, different guisettings and filepath -> different string
        up2.setGuiSettings(guiSettings2);
        up2.setSmartLibFilePath(filePath2);
        assertNotEquals(string, up2.toString());

        // different object, same filepath but different guisettings -> different string
        up2.setSmartLibFilePath(filePath);
        assertNotEquals(string, up2.toString());

        // different object, same guisettings but different filepath -> different string
        up2.setGuiSettings(guiSettings);
        up2.setSmartLibFilePath(filePath2);
        assertNotEquals(string, up2.toString());
    }

}
