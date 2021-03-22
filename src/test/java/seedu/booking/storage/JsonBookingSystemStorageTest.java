package seedu.booking.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.booking.testutil.Assert.assertThrows;
import static seedu.booking.testutil.TypicalPersons.ALICE;
import static seedu.booking.testutil.TypicalPersons.HOON;
import static seedu.booking.testutil.TypicalPersons.IDA;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.booking.commons.exceptions.DataConversionException;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.ReadOnlyBookingSystem;

public class JsonBookingSystemStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBookingSystemStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readBookingSystem_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBookingSystem(null));
    }

    private java.util.Optional<ReadOnlyBookingSystem> readBookingSystem(String filePath) throws Exception {
        return new JsonBookingSystemStorage(Paths.get(filePath))
                .readBookingSystem(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBookingSystem("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBookingSystem("notJsonFormatBookingSystem.json"));
    }

    @Test
    public void readBookingSystem_invalidPersonBookingSystem_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBookingSystem("invalidPersonBookingSystem.json"));
    }

    @Test
    public void readBookingSystem_invalidAndValidPersonBookingSystem_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBookingSystem("invalidAndValidPersonBookingSystem.json"));
    }

    @Test
    public void readAndSaveBookingSystem_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBookingSystem.json");
        BookingSystem original = getTypicalBookingSystem();
        JsonBookingSystemStorage jsonBookingSystemStorage = new JsonBookingSystemStorage(filePath);

        // Save in new file and read back
        jsonBookingSystemStorage.saveBookingSystem(original, filePath);
        ReadOnlyBookingSystem readBack = jsonBookingSystemStorage.readBookingSystem(filePath).get();
        assertEquals(original, new BookingSystem(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonBookingSystemStorage.saveBookingSystem(original, filePath);
        readBack = jsonBookingSystemStorage.readBookingSystem(filePath).get();
        assertEquals(original, new BookingSystem(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonBookingSystemStorage.saveBookingSystem(original); // file path not specified
        readBack = jsonBookingSystemStorage.readBookingSystem().get(); // file path not specified
        assertEquals(original, new BookingSystem(readBack));

    }

    @Test
    public void saveBookingSystem_nullBookingSystem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookingSystem(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveBookingSystem(ReadOnlyBookingSystem addressBook, String filePath) {
        try {
            new JsonBookingSystemStorage(Paths.get(filePath))
                    .saveBookingSystem(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveBookingSystem_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookingSystem(new BookingSystem(), null));
    }
}
