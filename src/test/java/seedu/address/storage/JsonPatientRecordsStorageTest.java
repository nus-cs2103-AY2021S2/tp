package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.ALICE;
import static seedu.address.testutil.TypicalAppObjects.HOON;
import static seedu.address.testutil.TypicalAppObjects.IDA;
import static seedu.address.testutil.TypicalAppObjects.getTypicalPatientRecords;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Patient;

public class JsonPatientRecordsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPatientRecordsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPatientRecords_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPatientRecords(null));
    }

    private java.util.Optional<ReadOnlyAddressBook<Patient>> readPatientRecords(String filePath) throws Exception {
        return new JsonPatientRecordsStorage(Paths.get(filePath)).readAddressBook(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPatientRecords("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPatientRecords("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readPatientRecords_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPatientRecords("invalidPersonAddressBook.json"));
    }

    @Test
    public void readPatientRecords_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPatientRecords("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSavePatientRecords_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AddressBook<Patient> original = getTypicalPatientRecords();
        JsonPatientRecordsStorage jsonPatientRecordsStorage = new JsonPatientRecordsStorage(filePath);

        // Save in new file and read back
        jsonPatientRecordsStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook<Patient> readBack = jsonPatientRecordsStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonPatientRecordsStorage.saveAddressBook(original, filePath);
        readBack = jsonPatientRecordsStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook<>(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonPatientRecordsStorage.saveAddressBook(original); // file path not specified
        readBack = jsonPatientRecordsStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook<>(readBack));

    }

    @Test
    public void savePatientRecords_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientRecords(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void savePatientRecords(ReadOnlyAddressBook<Patient> addressBook, String filePath) {
        try {
            new JsonPatientRecordsStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePatientRecords_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePatientRecords(new AddressBook<>(), null));
    }
}
