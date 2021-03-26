package fooddiary.model;

import static fooddiary.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ENTRY_A;
import static fooddiary.testutil.TypicalEntries.ENTRY_B;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fooddiary.commons.core.GuiSettings;
import fooddiary.model.entry.NameContainsKeywordsPredicate;
import fooddiary.testutil.FoodDiaryBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FoodDiary(), new FoodDiary(modelManager.getFoodDiary()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFoodDiaryFilePath(Paths.get("food/diary/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFoodDiaryFilePath(Paths.get("new/food/diary/file/path"));
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
    public void setFoodDiaryFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFoodDiaryFilePath(null));
    }

    @Test
    public void setFoodDiaryFilePath_validPath_setsFoodDiaryFilePath() {
        Path path = Paths.get("food/diary/file/path");
        modelManager.setFoodDiaryFilePath(path);
        assertEquals(path, modelManager.getFoodDiaryFilePath());
    }

    @Test
    public void hasEntry_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEntry(null));
    }

    @Test
    public void hasEntry_entryNotInFoodDiary_returnsFalse() {
        assertFalse(modelManager.hasEntry(ENTRY_A));
    }

    @Test
    public void hasEntry_entryInFoodDiary_returnsTrue() {
        modelManager.addEntry(ENTRY_A);
        assertTrue(modelManager.hasEntry(ENTRY_A));
    }

    @Test
    public void getFilteredEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEntryList().remove(0));
    }

    @Test
    public void equals() {
        FoodDiary foodDiary = new FoodDiaryBuilder().withEntry(ENTRY_A).withEntry(ENTRY_B).build();
        FoodDiary differentFoodDiary = new FoodDiary();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(foodDiary, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(foodDiary, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different foodDiary -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFoodDiary, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ENTRY_A.getName().fullName.split("\\s+");
        modelManager.updateFilteredEntryList(new NameContainsKeywordsPredicate(Arrays.asList(keywords[1])));
        assertFalse(modelManager.equals(new ModelManager(foodDiary, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFoodDiaryFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(foodDiary, differentUserPrefs)));
    }
}
