package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.ALICE;
import static seedu.address.testutil.TypicalRemindMe.HOON;
import static seedu.address.testutil.TypicalRemindMe.IDA;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.RemindMe;

public class JsonRemindMeStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRemindMeStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRemindMe_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRemindMe(null));
    }

    private java.util.Optional<ReadOnlyRemindMe> readRemindMe(String filePath) throws Exception {
        return new JsonRemindMeStorage(Paths.get(filePath)).readRemindMe(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRemindMe("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRemindMe("notJsonFormatRemindMe.json"));
    }

    @Test
    public void readRemindMe_invalidPersonRemindMe_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRemindMe("invalidPersonRemindMe.json"));
    }

    @Test
    public void readRemindMe_invalidAndValidPersonRemindMe_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRemindMe("invalidAndValidPersonRemindMe.json"));
    }

    @Test
    public void readAndSaveRemindMe_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRemindMe.json");
        RemindMe original = getTypicalRemindMe();
        JsonRemindMeStorage jsonRemindMeStorage = new JsonRemindMeStorage(filePath);

        // Save in new file and read back
        jsonRemindMeStorage.saveRemindMe(original, filePath);
        ReadOnlyRemindMe readBack = jsonRemindMeStorage.readRemindMe(filePath).get();
        assertEquals(original, new RemindMe(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonRemindMeStorage.saveRemindMe(original, filePath);
        readBack = jsonRemindMeStorage.readRemindMe(filePath).get();
        assertEquals(original, new RemindMe(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonRemindMeStorage.saveRemindMe(original); // file path not specified
        readBack = jsonRemindMeStorage.readRemindMe().get(); // file path not specified
        assertEquals(original, new RemindMe(readBack));

    }

    @Test
    public void saveRemindMe_nullRemindMe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRemindMe(null, "SomeFile.json"));
    }

    /**
     * Saves {@code remindMe} at the specified {@code filePath}.
     */
    private void saveRemindMe(ReadOnlyRemindMe remindMe, String filePath) {
        try {
            new JsonRemindMeStorage(Paths.get(filePath))
                    .saveRemindMe(remindMe, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRemindMe_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRemindMe(new RemindMe(), null));
    }
}
