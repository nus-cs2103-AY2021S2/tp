package seedu.smartlib.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.model.Model.PREDICATE_SHOW_ALL_READERS;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;
import seedu.smartlib.testutil.SmartLibBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new SmartLib(), new SmartLib(modelManager.getSmartLib()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSmartLibFilePath(Paths.get("smartLib/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSmartLibFilePath(Paths.get("new/smartLib/file/path"));
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
    public void setSmartLibFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSmartLibFilePath(null));
    }

    @Test
    public void setSmartLibFilePath_validPath_setsSmartLibFilePath() {
        Path path = Paths.get("smartLib/file/path");
        modelManager.setSmartLibFilePath(path);
        assertEquals(path, modelManager.getSmartLibFilePath());
    }

    @Test
    public void hasReader_nullReader_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasReader(null));
    }

    @Test
    public void hasReader_readerNotInSmartLib_returnsFalse() {
        assertFalse(modelManager.hasReader(ALICE));
    }

    @Test
    public void hasReader_readerInSmartLib_returnsTrue() {
        modelManager.addReader(ALICE);
        assertTrue(modelManager.hasReader(ALICE));
    }

    @Test
    public void getFilteredReaderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredReaderList().remove(0));
    }

    @Test
    public void equals() {
        SmartLib smartLib = new SmartLibBuilder().withReader(ALICE).withReader(BENSON).build();
        SmartLib differentSmartLib = new SmartLib();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(smartLib, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(smartLib, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different smartLib -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSmartLib, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredReaderList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(smartLib, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredReaderList(PREDICATE_SHOW_ALL_READERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSmartLibFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(smartLib, differentUserPrefs)));
    }
}
