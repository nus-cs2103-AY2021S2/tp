package seedu.iscam.storage.meeting;

import java.nio.file.Path;
import java.nio.file.Paths;

//import org.junit.jupiter.api.Test;
//
//import seedu.iscam.commons.exceptions.IllegalValueException;
//import seedu.iscam.commons.util.JsonUtil;
//import seedu.iscam.model.util.meetingbook.MeetingBook;
//import seedu.iscam.testutil.TypicalMeetings;

public class JsonSerializableMeetingBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMeetingBookTest");
    private static final Path TYPICAL_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("typicalMeetingsMeetingBook.json");
    private static final Path INVALID_MEETING_FILE = TEST_DATA_FOLDER.resolve("invalidMeetingMeetingBook.json");
    private static final Path DUPLICATE_MEETING_FILE = TEST_DATA_FOLDER.resolve("duplicateMeetingMeetingBook.json");

    //TODO: maybe fix this test
    /*
    @Test
    public void toModelType_typicalMeetingsFile_success() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEETINGS_FILE,
                JsonSerializableMeetingBook.class).get();
        MeetingBook clientBookFromFile = dataFromFile.toModelType();
        MeetingBook typicalMeetingsMeetingBook = TypicalMeetings.getTypicalMeetingBook();
        assertEquals(clientBookFromFile, typicalMeetingsMeetingBook);
    }
    */

    //TODO: maybe fix this test
    /*
    @Test
    public void toModelType_invalidMeetingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEETING_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }*/

    //TODO: maybe fix this test
    /*@Test
    public void toModelType_duplicateMeetings_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEETING_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMeetingBook.MESSAGE_DUPLICATE_MEETING,
                dataFromFile::toModelType);
    }*/

}
