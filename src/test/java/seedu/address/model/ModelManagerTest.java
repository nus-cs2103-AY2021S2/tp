package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.testutil.ColabFolderBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ColabFolder(), new ColabFolder(modelManager.getColabFolder()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setColabFolderFilePath(Paths.get("colab/folder/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setColabFolderFilePath(Paths.get("new/colab/folder/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setColabFolderFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setColabFolderFilePath(null));
    }

    @Test
    public void setColabFolderFilePath_validPath_setsColabFolderFilePath() {
        Path path = Paths.get("colab/folder/file/path");
        modelManager.setColabFolderFilePath(path);
        assertEquals(path, modelManager.getColabFolderFilePath());
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInColabFolder_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInColabFolder_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void equals() {
        ColabFolder colabFolder = new ColabFolderBuilder().withContact(ALICE).withContact(BENSON).build();
        ColabFolder differentColabFolder = new ColabFolder();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(colabFolder, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(colabFolder, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different colabFolder -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentColabFolder, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(colabFolder, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setColabFolderFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(colabFolder, differentUserPrefs)));
    }
}
