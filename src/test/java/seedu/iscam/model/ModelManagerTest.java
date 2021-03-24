package seedu.iscam.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.iscam.testutil.Assert.assertThrows;
import static seedu.iscam.testutil.TypicalClients.ALICE;
import static seedu.iscam.testutil.TypicalClients.BENSON;
import static seedu.iscam.testutil.TypicalMeetings.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.model.client.NameContainsKeywordsPredicate;
import seedu.iscam.testutil.ClientBookBuilder;
import seedu.iscam.testutil.MeetingBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ClientBook(), new ClientBook(modelManager.getClientBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setClientBookFilePath(Paths.get("iscam/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setClientBookFilePath(Paths.get("new/iscam/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setClientBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("iscam/book/file/path");
        modelManager.setClientBookFilePath(path);
        assertEquals(path, modelManager.getClientBookFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void equals() {
        ClientBook clientBook = new ClientBookBuilder().withClient(ALICE).withClient(BENSON).build();
        ClientBook differentClientBook = new ClientBook();

        UserPrefs userPrefs = new UserPrefs();

        MeetingBook meetingBook = new MeetingBookBuilder().withMeeting(ALICE_1).withMeeting(BENSON_1).build();
        MeetingBook differentMeetingBook = new MeetingBook();

        // same values -> returns true
        modelManager = new ModelManager(clientBook, meetingBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(clientBook, meetingBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different clientBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentClientBook, differentMeetingBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(clientBook, meetingBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setClientBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(clientBook, meetingBook, differentUserPrefs)));
    }
}
