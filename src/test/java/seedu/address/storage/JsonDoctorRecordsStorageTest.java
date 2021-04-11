package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppObjects.DR_GREY;
import static seedu.address.testutil.TypicalAppObjects.DR_MURPHY;
import static seedu.address.testutil.TypicalAppObjects.DR_DRAKE;
import static seedu.address.testutil.TypicalAppObjects.getTypicalDoctorRecords;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Doctor;

public class JsonDoctorRecordsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDoctorRecordsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDoctorRecords_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDoctorRecords(null));
    }

    private java.util.Optional<ReadOnlyAddressBook<Doctor>> readDoctorRecords(String filePath) throws Exception {
        return new JsonDoctorRecordsStorage(Paths.get(filePath)).readAddressBook(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDoctorRecords("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readDoctorRecords("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readDoctorRecords_invalidDoctorRecords_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDoctorRecords("invalidDoctorRecords.json"));
    }

    @Test
    public void readDoctorRecords_invalidAndValidDoctorRecords_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDoctorRecords(
                "invalidAndValidDoctorRecords.json"));
    }

    @Test
    public void readAndSaveDoctorRecords_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDoctorRecords.json");
        AddressBook<Doctor> original = getTypicalDoctorRecords();
        JsonDoctorRecordsStorage jsonDoctorRecordsStorage = new JsonDoctorRecordsStorage(filePath);

        // Save in new file and read back
        jsonDoctorRecordsStorage.saveAddressBook(original, filePath);
        ReadOnlyAddressBook<Doctor> readBack = jsonDoctorRecordsStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(DR_MURPHY);
        original.removePerson(DR_GREY);
        jsonDoctorRecordsStorage.saveAddressBook(original, filePath);
        readBack = jsonDoctorRecordsStorage.readAddressBook(filePath).get();
        assertEquals(original, new AddressBook<>(readBack));

        // Save and read without specifying file path
        original.addPerson(DR_DRAKE);
        jsonDoctorRecordsStorage.saveAddressBook(original); // file path not specified
        readBack = jsonDoctorRecordsStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook<>(readBack));

    }

    @Test
    public void saveDoctorRecords_nullDoctorRecords_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDoctorRecords(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveDoctorRecords(ReadOnlyAddressBook<Doctor> doctorRecords, String filePath) {
        try {
            new JsonDoctorRecordsStorage(Paths.get(filePath))
                    .saveAddressBook(doctorRecords, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDoctorRecords_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDoctorRecords(new AddressBook<>(), null));
    }
}
