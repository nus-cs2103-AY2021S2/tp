package seedu.smartlib.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.HOON;
import static seedu.smartlib.testutil.TypicalModels.IDA;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartlib.commons.exceptions.DataConversionException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;

public class JsonSmartLibStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSmartLibStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSmartLib_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSmartLib(null));
    }

    private java.util.Optional<ReadOnlySmartLib> readSmartLib(String filePath) throws Exception {
        return new JsonSmartLibStorage(Paths.get(filePath)).readSmartLib(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSmartLib("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSmartLib("notJsonFormatSmartLib.json"));
    }

    @Test
    public void readSmartLib_invalidReaderSmartLib_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartLib("invalidReaderSmartLib.json"));
    }

    @Test
    public void readSmartLib_invalidAndValidReaderSmartLib_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartLib("invalidAndValidReaderSmartLib.json"));
    }

    @Test
    public void readAndSaveSmartLib_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        SmartLib original = getTypicalSmartLib();
        JsonSmartLibStorage jsonAddressBookStorage = new JsonSmartLibStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveSmartLib(original, filePath);
        ReadOnlySmartLib readBack = jsonAddressBookStorage.readSmartLib(filePath).get();
        assertEquals(original, new SmartLib(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addReader(HOON);
        original.removeReader(ALICE);
        jsonAddressBookStorage.saveSmartLib(original, filePath);
        readBack = jsonAddressBookStorage.readSmartLib(filePath).get();
        assertEquals(original, new SmartLib(readBack));

        // Save and read without specifying file path
        original.addReader(IDA);
        jsonAddressBookStorage.saveSmartLib(original); // file path not specified
        readBack = jsonAddressBookStorage.readSmartLib().get(); // file path not specified
        assertEquals(original, new SmartLib(readBack));

    }

    @Test
    public void saveSmartLib_nullSmartLib_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSmartLib(null, "SomeFile.json"));
    }

    /**
     * Saves {@code smartLib} at the specified {@code filePath}.
     */
    private void saveSmartLib(ReadOnlySmartLib smartLib, String filePath) {
        try {
            new JsonSmartLibStorage(Paths.get(filePath))
                    .saveSmartLib(smartLib, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSmartLib_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSmartLib(new SmartLib(), null));
    }
}
