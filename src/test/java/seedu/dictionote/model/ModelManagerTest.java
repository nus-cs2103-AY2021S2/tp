package seedu.dictionote.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalContacts.ALICE;
import static seedu.dictionote.testutil.TypicalContacts.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.model.contact.NameContainsKeywordsPredicate;
import seedu.dictionote.testutil.ContactsListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ContactsList(), new ContactsList(modelManager.getContactsList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setContactsListFilePath(Paths.get("dictionote/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4, 5,
            6, 7, 8, true, false,
            true, false, true));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setContactsListFilePath(Paths.get("new/dictionote/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4, 5,
            6, 7, 8, true, false,
            true, false, true);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setContactsListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setContactsFilePath(null));
    }

    @Test
    public void setContactsListFilePath_validPath_setsContactsListFilePath() {
        Path path = Paths.get("dictionote/book/file/path");
        modelManager.setContactsFilePath(path);
        assertEquals(path, modelManager.getContactsListFilePath());
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInContactsList_returnsFalse() {
        assertFalse(modelManager.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactsList_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.hasContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void equals() {
        ContactsList contactsList = new ContactsListBuilder().withContact(ALICE).withContact(BENSON).build();
        ContactsList differentContactsList = new ContactsList();
        UserPrefs userPrefs = new UserPrefs();
        NoteBook noteBook = new NoteBook();
        Dictionary dictionary = new Dictionary();
        DefinitionBook definitionBook = new DefinitionBook();

        // same values -> returns true
        modelManager = new ModelManager(contactsList, userPrefs, noteBook, dictionary, definitionBook);
        ModelManager modelManagerCopy = new ModelManager(contactsList, userPrefs, noteBook, dictionary, definitionBook);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentContactsList, userPrefs, noteBook,
                dictionary, definitionBook)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredContactList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(contactsList, userPrefs, noteBook,
                dictionary, definitionBook)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setContactsListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(contactsList, differentUserPrefs, noteBook,
                dictionary, definitionBook)));
    }
}
