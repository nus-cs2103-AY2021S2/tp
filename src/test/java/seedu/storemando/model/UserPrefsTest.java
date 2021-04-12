package seedu.storemando.model;

import static seedu.storemando.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setStoreMandoFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setStoreMandoFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPref = new UserPrefs();

        assertTrue(userPref.equals(userPref));
        assertTrue(!userPref.equals(null));
    }
}
