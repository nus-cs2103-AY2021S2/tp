package seedu.address.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ReadOnlyAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import static seedu.address.testutil.TypicalMeetings.*;
import static seedu.address.testutil.TypicalPersons.IDA;

public class JsonMeetingBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMeetingBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMeetingsBook_nullFilePath_throwsNullPointerException() {
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
    public void readMeetingBook_missingFile_returnsEmptyOptional() throws Exception {
        assertEquals(readMeetingBook("NonExistentFile.json"), Optional.empty());
    }

    @Test
    public void readMeetingBook_invalidMeetingFile_throwsDataConversionException() throws Exception {
        assertThrows(DataConversionException.class, () -> readMeetingBook("invalidMeetingMeetingBook.json") );
    }

    @Test

    public void readMeetingBook_invalidJsonFormatFile_throwsDataConversionException() throws Exception {
        assertThrows(DataConversionException.class, () -> readMeetingBook("notJsonFormatMeetingBook.json"));
    }

    @Test
    public void readMeetingBook_invalidAndValidMeetingFile_throwsDataConversionException() throws Exception {
        assertThrows(DataConversionException.class, () -> readMeetingBook("invalidAndValidMeetingMeetingBook.json"));
    }

    @Test
    public void readAndSaveMeetingBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        MeetingBook original = getTypicalMeetingBook();
        JsonMeetingBookStorage jsonMeetingBookStorage = new JsonMeetingBookStorage(filePath);

        // Save in new file and read back
        jsonMeetingBookStorage.saveMeetingBook(original, filePath);
        ReadOnlyMeetingBook readBack = jsonMeetingBookStorage.readMeetingBook(filePath).get();
        assertEquals(original, new MeetingBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addMeeting(MEETING6);
        original.removeMeeting(MEETING1);
        jsonMeetingBookStorage.saveMeetingBook(original, filePath);
        readBack = jsonMeetingBookStorage.readMeetingBook(filePath).get();
        assertEquals(original, new MeetingBook(readBack));

        // Save and read without specifying file path
        original.addMeeting(MEETING1);
        jsonMeetingBookStorage.saveMeetingBook(original); // file path not specified
        readBack = jsonMeetingBookStorage.readMeetingBook().get(); // file path not specified
        assertEquals(original, new MeetingBook(readBack));
    }

    @Test
    public void saveMeetingBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMeetingBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code MeetingBook} at the specified {@code filePath}.
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
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMeetingBook(new MeetingBook(), null));
    }


}
