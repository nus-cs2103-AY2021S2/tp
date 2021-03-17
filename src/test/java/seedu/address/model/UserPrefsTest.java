package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.DeadlineBuilder;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs testUserPref = new UserPrefs();

        // same values -> returns true
        UserPrefs userPrefsCopy = new UserPrefs(testUserPref);
        assertEquals(testUserPref, userPrefsCopy);

        // same object -> returns true
        assertEquals(testUserPref, testUserPref);

        // null -> returns false
        assertNotEquals(testUserPref, null);

        // different type -> returns false
        assertNotEquals(testUserPref, 5);

        // different guiSettings -> returns false
        UserPrefs differentUserPrefs1 = new UserPrefs();
        differentUserPrefs1.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        assertNotEquals(differentUserPrefs1, testUserPref);

        // different addressBookFilePath -> returns false
        UserPrefs differentUserPrefs2 = new UserPrefs();
        differentUserPrefs2.setAddressBookFilePath(Paths.get("data" , "test.json"));
        assertNotEquals(differentUserPrefs2, testUserPref);

        // different projectsFolderFilePath -> returns false
        UserPrefs differentUserPrefs3 = new UserPrefs();
        differentUserPrefs3.setProjectsFolderFilePath(Paths.get("data" , "test.json"));
        assertNotEquals(differentUserPrefs3, testUserPref);

    }

    @Test
    public void hashCode_success() {
        UserPrefs userPref1 = new UserPrefs();
        UserPrefs userPref2 = new UserPrefs();
        userPref2.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        UserPrefs userPref3 = new UserPrefs();
        userPref3.setAddressBookFilePath(Paths.get("data" , "test.json"));
        UserPrefs userPref4 = new UserPrefs();
        userPref4.setProjectsFolderFilePath(Paths.get("data" , "test.json"));
        int hashcode1 = userPref1.hashCode();
        int hashcode2 = userPref2.hashCode();
        int hashcode3 = userPref3.hashCode();
        int hashcode4 = userPref4.hashCode();
        assertEquals(hashcode1, userPref1.hashCode());
        assertNotEquals(hashcode1, hashcode2);
        assertNotEquals(hashcode1, hashcode3);
        assertNotEquals(hashcode1, hashcode4);
    }

}
