package seedu.iscam.storage.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.iscam.testutil.Assert.assertThrows;
import static seedu.iscam.testutil.TypicalMeetings.ALICE_1;
import static seedu.iscam.testutil.TypicalMeetings.HOON;
import static seedu.iscam.testutil.TypicalMeetings.IGUANA;
import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.iscam.commons.exceptions.DataConversionException;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;

public class JsonMeetingBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMeetingBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMeetingBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMeetingBook(null));
    }

    private java.util.Optional<ReadOnlyMeetingBook> readMeetingBook(String filePath) throws Exception {
        return new JsonMeetingBookStorage(Paths.get(filePath)).readMeetingBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMeetingBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMeetingBook("notJsonFormatMeetingBook.json"));
    }

    @Test
    public void readMeetingBook_invalidMeetingMeetingBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMeetingBook("invalidMeetingMeetingBook.json"));
    }

    @Test
    public void readMeetingBook_invalidAndValidMeetingMeetingBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMeetingBook("invalidAndValidMeetingMeetingBook.json"));
    }

    @Test
    public void readAndSaveMeetingBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMeetingBook.json");
        MeetingBook original = getTypicalMeetingBook();
        JsonMeetingBookStorage jsonMeetingBookStorage = new JsonMeetingBookStorage(filePath);

        // Save in new file and read back
        jsonMeetingBookStorage.saveMeetingBook(original, filePath);
        ReadOnlyMeetingBook readBack = jsonMeetingBookStorage.readMeetingBook(filePath).get();
        assertEquals(original, new MeetingBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addMeeting(HOON);
        original.removeMeeting(ALICE_1);
        jsonMeetingBookStorage.saveMeetingBook(original, filePath);
        readBack = jsonMeetingBookStorage.readMeetingBook(filePath).get();
        assertEquals(original, new MeetingBook(readBack));

        // Save and read without specifying file path
        original.addMeeting(IGUANA);
        jsonMeetingBookStorage.saveMeetingBook(original); // file path not specified
        readBack = jsonMeetingBookStorage.readMeetingBook().get(); // file path not specified
        assertEquals(original, new MeetingBook(readBack));

    }

    @Test
    public void saveMeetingBook_nullMeetingBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMeetingBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code meetingBook} at the specified {@code filePath}.
     */
    private void saveMeetingBook(ReadOnlyMeetingBook meetingBook, String filePath) {
        try {
            new JsonMeetingBookStorage(Paths.get(filePath))
                    .saveMeetingBook(meetingBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMeetingBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMeetingBook(new MeetingBook(), null));
    }
}
