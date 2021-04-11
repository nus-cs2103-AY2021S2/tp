package seedu.iscam.model;

import static seedu.iscam.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.iscam.model.user.UserPrefs;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setClientBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setClientBookFilePath(null));
    }

    @Test
    public void setMeetingBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setMeetingBookFilePath(null));
    }

}
