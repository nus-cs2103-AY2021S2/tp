package seedu.iscam.storage.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.iscam.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.commons.util.JsonUtil;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.testutil.TypicalMeetings;

public class JsonSerializableMeetingBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMeetingBookTest");
    private static final Path TYPICAL_MEETING_FILE = TEST_DATA_FOLDER.resolve("typicalMeetingsMeetingBook.json");
    private static final Path INVALID_MEETING_FILE = TEST_DATA_FOLDER.resolve("invalidMeetingMeetingBook.json");
    private static final Path DUPLICATE_MEETING_FILE = TEST_DATA_FOLDER.resolve("duplicateMeetingMeetingBook.json");

    @Test
    public void toModelType_typicalMeetingsFile_success() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEETING_FILE,
                JsonSerializableMeetingBook.class).get();
        MeetingBook meetingBookFromFile = dataFromFile.toModelType();
        MeetingBook typicalMeetingsMeetingBook = TypicalMeetings.getTypicalMeetingBook();
        assertEquals(meetingBookFromFile, typicalMeetingsMeetingBook);
    }

    @Test
    public void toModelType_invalidMeetingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEETING_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateMeetings_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEETING_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMeetingBook.MESSAGE_DUPLICATE_MEETING,
                dataFromFile::toModelType);
    }

}
