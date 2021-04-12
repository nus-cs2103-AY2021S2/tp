package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalResidences.RESIDENCE_A;
import static seedu.address.testutil.TypicalResidences.RESIDENCE_B;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.residence.NameContainsKeywordsPredicate;
import seedu.address.testutil.ResidenceTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ResidenceTracker(), new ResidenceTracker(modelManager.getResidenceTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setResidenceTrackerFilePath(Paths.get("residence/tracker/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setResidenceTrackerFilePath(Paths.get("new/residence/tracker/file/path"));
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
    public void setResidenceTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setResidenceTrackerFilePath(null));
    }

    @Test
    public void setResidenceTrackerFilePath_validPath_setsResidenceTrackerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setResidenceTrackerFilePath(path);
        assertEquals(path, modelManager.getResidenceTrackerFilePath());
    }

    @Test
    public void hasResidence_nullResidence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasResidence(null));
    }

    @Test
    public void hasResidence_personNotInResidenceTracker_returnsFalse() {
        assertFalse(modelManager.hasResidence(RESIDENCE_A));
    }

    @Test
    public void hasResidence_residenceInResidenceTracker_returnsTrue() {
        modelManager.addResidence(RESIDENCE_A);
        assertTrue(modelManager.hasResidence(RESIDENCE_A));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredResidenceList().remove(0));
    }

    @Test
    public void equals() {
        ResidenceTracker residenceTracker = new ResidenceTrackerBuilder().withResidence(RESIDENCE_A)
                .withResidence(RESIDENCE_B).build();
        ResidenceTracker differentAddressBook = new ResidenceTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(residenceTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(residenceTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = RESIDENCE_A.getResidenceName().toString().split("\\s+");
        modelManager.updateFilteredResidenceList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(residenceTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setResidenceTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(residenceTracker, differentUserPrefs)));
    }
}
