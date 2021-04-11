package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDietLah.getTypicalDietLah;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.User;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new DietLah(), new DietLah(modelManager.getDietLah()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setDietLahFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setDietLahFilePath(Paths.get("new/address/book/file/path"));
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
    public void setDietLahFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setDietLahFilePath(null));
    }

    @Test
    public void setDietLahFilePath_validPath_setsDietLahFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setDietLahFilePath(path);
        assertEquals(path, modelManager.getDietLahFilePath());
    }

    @Test
    public void equals() {
        DietLah dietLah = getTypicalDietLah();
        DietLah differentDietLah = new DietLah();
        UserPrefs userPrefs = new UserPrefs();
        User user = new User();


        // same values -> returns true
        modelManager = new ModelManager(new UniqueFoodList(),
                new FoodIntakeList(), new DietPlanList(), userPrefs, user);
        ModelManager modelManagerCopy = new ModelManager(new UniqueFoodList(),
                new FoodIntakeList(), new DietPlanList(), userPrefs, user);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different dietLah -> returns false
        assertFalse(modelManager.equals(new ModelManager(dietLah.getFoodList(),
                dietLah.getFoodIntakeList(), new DietPlanList(), userPrefs,
                dietLah.getUser())));

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setDietLahFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(new UniqueFoodList(),
                new FoodIntakeList(), new DietPlanList(), differentUserPrefs, user)));
    }
}
