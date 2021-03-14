package seedu.iScam.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.iScam.testutil.Assert.assertThrows;
import static seedu.iScam.testutil.TypicalClients.ALICE;
import static seedu.iScam.testutil.TypicalClients.HOON;
import static seedu.iScam.testutil.TypicalClients.IDA;
import static seedu.iScam.testutil.TypicalClients.getTypicalLocationBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.iScam.commons.exceptions.DataConversionException;
import seedu.iScam.model.ClientBook;
import seedu.iScam.model.ReadOnlyClientBook;

public class JsonClientBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClientBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClientBook(null));
    }

    private java.util.Optional<ReadOnlyClientBook> readClientBook(String filePath) throws Exception {
        return new JsonClientBookStorage(Paths.get(filePath)).readClientBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClientBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readClientBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidClientAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientBook("invalidClientAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidClientAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientBook("invalidAndValidClientAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        ClientBook original = getTypicalLocationBook();
        JsonClientBookStorage jsonAddressBookStorage = new JsonClientBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveClientBook(original, filePath);
        ReadOnlyClientBook readBack = jsonAddressBookStorage.readClientBook(filePath).get();
        assertEquals(original, new ClientBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonAddressBookStorage.saveClientBook(original, filePath);
        readBack = jsonAddressBookStorage.readClientBook(filePath).get();
        assertEquals(original, new ClientBook(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonAddressBookStorage.saveClientBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readClientBook().get(); // file path not specified
        assertEquals(original, new ClientBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyClientBook addressBook, String filePath) {
        try {
            new JsonClientBookStorage(Paths.get(filePath))
                    .saveClientBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new ClientBook(), null));
    }
}
