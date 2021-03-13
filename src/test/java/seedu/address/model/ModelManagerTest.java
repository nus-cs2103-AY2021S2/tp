package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GARMENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGarments.ALICE;
import static seedu.address.testutil.TypicalGarments.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.garment.NameContainsKeywordsPredicate;
import seedu.address.testutil.WardrobeBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Wardrobe(), new Wardrobe(modelManager.getWardrobe()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setWardrobeFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setWardrobeFilePath(Paths.get("new/address/book/file/path"));
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
    public void setWardrobeFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setWardrobeFilePath(null));
    }

    @Test
    public void setWardrobeFilePath_validPath_setsWardrobeFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setWardrobeFilePath(path);
        assertEquals(path, modelManager.getWardrobeFilePath());
    }

    @Test
    public void hasGarment_nullGarment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasGarment(null));
    }

    @Test
    public void hasGarment_garmentNotInWardrobe_returnsFalse() {
        assertFalse(modelManager.hasGarment(ALICE));
    }

    @Test
    public void hasGarment_garmentInWardrobe_returnsTrue() {
        modelManager.addGarment(ALICE);
        assertTrue(modelManager.hasGarment(ALICE));
    }

    @Test
    public void getFilteredGarmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredGarmentList().remove(0));
    }

    @Test
    public void equals() {
        Wardrobe wardrobe = new WardrobeBuilder().withGarment(ALICE).withGarment(BENSON).build();
        Wardrobe differentWardrobe = new Wardrobe();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(wardrobe, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(wardrobe, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different Wardrobe -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentWardrobe, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredGarmentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(wardrobe, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredGarmentList(PREDICATE_SHOW_ALL_GARMENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setWardrobeFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(wardrobe, differentUserPrefs)));
    }
}
