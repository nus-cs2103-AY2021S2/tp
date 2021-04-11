package seedu.address.storage.meetings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.storage.meetingbook.JsonSerializableMeetingBook;
import seedu.address.testutil.TypicalMeetings;


public class JsonSerializableMeetingBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMeetingBookTest");
    private static final Path TYPICAL_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("sampleMeetingBook.json");
    private static final Path INVALID_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("invalidMeetingsMeetingBook.json");
    private static final Path DUPLICATE_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("duplicateMeetingsMeetingBook.json");
    private static final Path CLASHING_MEETINGS_FILE = TEST_DATA_FOLDER.resolve("clashingMeetingsMeetingBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_MEETINGS_FILE,
                JsonSerializableMeetingBook.class).get();
        MeetingBook meetingBookFromFile = dataFromFile.toModelType();
        MeetingBook typicalPersonsAddressBook = TypicalMeetings.getSampleMeetingBook();
        assertEquals(meetingBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidMeetingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(INVALID_MEETINGS_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MEETINGS_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMeetingBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_clashingMeetings_throwsIllegalValueException() throws Exception {
        JsonSerializableMeetingBook dataFromFile = JsonUtil.readJsonFile(CLASHING_MEETINGS_FILE,
                JsonSerializableMeetingBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMeetingBook.MESSAGE_CLASHING_MEETINGS,
                dataFromFile::toModelType);
    }


}
