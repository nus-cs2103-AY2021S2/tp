package seedu.us.among.model;

import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.us.among.testutil.Assert;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setEndpointListFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPrefs.setEndpointListFilePath(null));
    }

}
