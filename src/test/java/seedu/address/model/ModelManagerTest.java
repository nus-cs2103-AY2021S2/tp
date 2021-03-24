package seedu.address.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.SocheduleBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Sochedule(), new Sochedule(modelManager.getSochedule()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setSocheduleFilePath(Paths.get("sochedule/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setSocheduleFilePath(Paths.get("new/sochedule/file/path"));
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
    public void setSocheduleFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setSocheduleFilePath(null));
    }

    @Test
    public void setSocheduleFilePath_validPath_setsSocheduleFilePath() {
        Path path = Paths.get("sochedule/file/path");
        modelManager.setSocheduleFilePath(path);
        assertEquals(path, modelManager.getSocheduleFilePath());
    }

    // ----- Task ------

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInSochedule_returnsFalse() {
        assertFalse(modelManager.hasTask(ASSIGNMENT));
    }

    @Test
    public void hasTask_taskInSochedule_returnsTrue() {
        modelManager.addTask(ASSIGNMENT);
        assertTrue(modelManager.hasTask(ASSIGNMENT));
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTaskList().remove(0));
    }

    // ----- Event ------

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInSochedule_returnsFalse() {
        assertFalse(modelManager.hasEvent(MEETING));
    }

    @Test
    public void hasEvent_eventInSochedule_returnsTrue() {
        modelManager.addEvent(MEETING);
        assertTrue(modelManager.hasEvent(MEETING));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void equals() {
        Sochedule sochedule = new SocheduleBuilder().withTask(ASSIGNMENT).withEvent(MEETING).build();
        Sochedule differentSochedule = new Sochedule();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(sochedule, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(sochedule, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different sochedule -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentSochedule, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ASSIGNMENT.getName().fullName.split("\\s+");
        modelManager.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        modelManager.updateFilteredEventList(new EventNameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(sochedule, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);


        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setSocheduleFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(sochedule, differentUserPrefs)));
    }
}
