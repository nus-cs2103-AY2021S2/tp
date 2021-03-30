package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.CS2030;
import static seedu.address.testutil.TypicalEvents.CS2107;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.EventContainsKeywordsPredicate;
import seedu.address.testutil.EventBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new EventBook(), new EventBook(modelManager.getEventBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setEventBookFilePath(Paths.get("event/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setEventBookFilePath(Paths.get("new/event/book/file/path"));
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
    public void setEventBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEventBookFilePath(null));
    }

    @Test
    public void setEventBookFilePath_validPath_setsEventBookFilePath() {
        Path path = Paths.get("event/book/file/path");
        modelManager.setEventBookFilePath(path);
        assertEquals(path, modelManager.getEventBookFilePath());
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInEventBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(CS2030));
    }

    @Test
    public void hasEvent_eventInEventBook_returnsTrue() {
        modelManager.addEvent(CS2030);
        assertTrue(modelManager.hasEvent(CS2030));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void equals() {
        EventBook eventBook = new EventBookBuilder().withEvent(CS2030).withEvent(CS2107).build();
        EventBook differentEventBook = new EventBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(userPrefs, eventBook);
        ModelManager modelManagerCopy = new ModelManager(userPrefs, eventBook);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different eventBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(userPrefs, differentEventBook)));

        // different filteredEventList -> returns false
        String[] eventKeywords = CS2030.getName().eventName.split("\\s+");
        modelManager.updateFilteredEventList(new EventContainsKeywordsPredicate(Arrays.asList(eventKeywords)));
        assertFalse(modelManager.equals(new ModelManager(userPrefs, eventBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setEventBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(differentUserPrefs, eventBook)));
    }
}
