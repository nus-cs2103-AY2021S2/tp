package seedu.iscam.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.iscam.testutil.Assert.assertThrows;
//import static seedu.iscam.testutil.TypicalMeetings.ALICE_1;
//import static seedu.iscam.testutil.TypicalMeetings.CARL_1;
//import static seedu.iscam.testutil.TypicalMeetings.DANIEL_1;
//import static seedu.iscam.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

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
