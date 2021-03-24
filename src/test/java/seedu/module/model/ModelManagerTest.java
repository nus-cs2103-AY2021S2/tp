package seedu.module.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.MIDTERM;
import static seedu.module.testutil.TypicalTasks.QUIZ;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.module.commons.core.GuiSettings;
import seedu.module.model.task.NameContainsKeywordsPredicate;
import seedu.module.model.task.Task;
import seedu.module.testutil.ModuleBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ModuleBook(), new ModuleBook(modelManager.getModuleBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setModuleBookFilePath(Paths.get("description/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setModuleBookFilePath(Paths.get("new/description/book/file/path"));
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
    public void setModuleBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setModuleBookFilePath(null));
    }

    @Test
    public void setModuleBookFilePath_validPath_setsModuleBookFilePath() {
        Path path = Paths.get("description/book/file/path");
        modelManager.setModuleBookFilePath(path);
        assertEquals(path, modelManager.getModuleBookFilePath());
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInModuleBook_returnsFalse() {
        assertFalse(modelManager.hasTask(QUIZ));
    }

    @Test
    public void hasTask_taskInModuleBook_returnsTrue() {
        modelManager.addTask(QUIZ);
        assertTrue(modelManager.hasTask(QUIZ));
    }

    @Test
    public void sortTasks_unsortedTasksInModuleBook_returnsSortedTasks() {
        modelManager.addTask(MIDTERM);
        modelManager.addTask(QUIZ);
        modelManager.sortTasks(new Task.DeadlineComparator());
        assertEquals(QUIZ, modelManager.getFilteredTaskList().get(0));
        assertEquals(MIDTERM, modelManager.getFilteredTaskList().get(1));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    @Test
    public void equals() {
        ModuleBook moduleBook = new ModuleBookBuilder().withTask(QUIZ).withTask(MIDTERM).build();
        ModuleBook differentModuleBook = new ModuleBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(moduleBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(moduleBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different moduleBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentModuleBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = QUIZ.getName().fullName.split("\\s+");
        modelManager.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(moduleBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setModuleBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(moduleBook, differentUserPrefs)));
    }
}
