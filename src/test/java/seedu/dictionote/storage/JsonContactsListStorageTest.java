package seedu.dictionote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalContacts.ALICE;
import static seedu.dictionote.testutil.TypicalContacts.HOON;
import static seedu.dictionote.testutil.TypicalContacts.IDA;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.model.ReadOnlyContactsList;

public class JsonContactsListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonContactsListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readContactsList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readContactsList(null));
    }

    private java.util.Optional<ReadOnlyContactsList> readContactsList(String filePath) throws Exception {
        return new JsonContactsListStorage(Paths.get(filePath)).readContactsList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readContactsList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readContactsList("notJsonFormatContactsList.json"));
    }

    @Test
    public void readContactsList_invalidContactsList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readContactsList("invalidContactsList.json"));
    }

    @Test
    public void readContactsList_invalidAndValidContactsList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readContactsList("invalidAndValidContactsList.json"));
    }

    @Test
    public void readAndSaveContactsList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ContactsList original = getTypicalContactsList();
        JsonContactsListStorage jsonContactsListStorage = new JsonContactsListStorage(filePath);

        // Save in new file and read back
        jsonContactsListStorage.saveContactsList(original, filePath);
        ReadOnlyContactsList readBack = jsonContactsListStorage.readContactsList(filePath).get();
        assertEquals(original, new ContactsList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addContact(HOON);
        original.removeContact(ALICE);
        jsonContactsListStorage.saveContactsList(original, filePath);
        readBack = jsonContactsListStorage.readContactsList(filePath).get();
        assertEquals(original, new ContactsList(readBack));

        // Save and read without specifying file path
        original.addContact(IDA);
        jsonContactsListStorage.saveContactsList(original); // file path not specified
        readBack = jsonContactsListStorage.readContactsList().get(); // file path not specified
        assertEquals(original, new ContactsList(readBack));

    }

    @Test
    public void saveContactsList_nullContactsList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveContactsList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveContactsList(ReadOnlyContactsList addressBook, String filePath) {
        try {
            new JsonContactsListStorage(Paths.get(filePath))
                    .saveContactsList(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveContactsList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveContactsList(new ContactsList(), null));
    }
}
