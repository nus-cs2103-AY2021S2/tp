package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2040;
import static seedu.address.testutil.TypicalTasks.CS2103;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.TaskTrackerBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TaskTracker(), new TaskTracker(modelManager.getTaskTracker()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskTrackerFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskTrackerFilePath(Paths.get("new/address/book/file/path"));
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
    public void setTaskTrackerFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskTrackerFilePath(null));
    }

    @Test
    public void setTaskTrackerFilePath_validPath_setsTaskTrackerFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTaskTrackerFilePath(path);
        assertEquals(path, modelManager.getTaskTrackerFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskTracker_returnsFalse() {
        assertFalse(modelManager.hasTask(CS2103));
    }

    @Test
    public void hasTask_taskInTaskTracker_returnsTrue() {
        modelManager.addTask(CS2103);
        assertTrue(modelManager.hasTask(CS2103));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        TaskTracker taskTracker = new TaskTrackerBuilder().withTask(CS2103).withTask(CS2040).build();
        TaskTracker differentTaskTracker = new TaskTracker();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taskTracker, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taskTracker, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskTracker -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskTracker, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = CS2103.getTaskName().fullName.split("\\s+");
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(taskTracker, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskTrackerFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskTracker, differentUserPrefs)));
    }
}
