package seedu.dictionote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.NoteBook;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonContactsListStorage addressBookStorage = new JsonContactsListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonNoteBookStorage noteBookStorage = new JsonNoteBookStorage(getTempFilePath("nb"));
        JsonDictionaryStorage dictionaryStorage = new JsonDictionaryStorage(getTempFilePath("dc"));
        JsonDefinitionBookStorage definitionBookStorage = new JsonDefinitionBookStorage(getTempFilePath("dbs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, noteBookStorage,
                dictionaryStorage, definitionBookStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6,
            0.5, 0.5, 0.5, 0.5, false, true, false, true, false, true, true));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void contactsListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        ContactsList original = getTypicalContactsList();
        storageManager.saveContactsList(original);
        ReadOnlyContactsList retrieved = storageManager.readContactsList().get();
        assertEquals(original, new ContactsList(retrieved));
    }

    @Test
    public void getContactsListFilePath() {
        assertNotNull(storageManager.getContactsListFilePath());
    }

    @Test
    public void noteBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonNoteBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonNoteBookStorageTest} class.
         */
        NoteBook original = getTypicalNoteBook();
        storageManager.saveNoteBook(original);
        ReadOnlyNoteBook retrieved = storageManager.readNoteBook().get();
        assertEquals(original, new NoteBook(retrieved));
    }

    @Test
    public void getNoteBookFilePath() {
        assertNotNull(storageManager.getNoteBookFilePath());
    }


}
