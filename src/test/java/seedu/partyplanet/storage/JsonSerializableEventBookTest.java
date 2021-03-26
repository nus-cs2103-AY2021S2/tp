package seedu.partyplanet.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.commons.exceptions.IllegalValueException;
import seedu.partyplanet.commons.util.JsonUtil;
import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.testutil.TypicalEvents;

public class JsonSerializableEventBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableEventBookTest");
    private static final Path TYPICAL_EVENTS_FILE = TEST_DATA_FOLDER.resolve("typicalEventsEventBook.json");
    private static final Path INVALID_EVENT_FILE = TEST_DATA_FOLDER.resolve("invalidEventEventBook.json");
    private static final Path DUPLICATE_EVENT_FILE = TEST_DATA_FOLDER.resolve("duplicateEventEventBook.json");

    @Test
    public void toModelType_typicalEventsFile_success() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EVENTS_FILE,
                JsonSerializableEventBook.class).get();
        EventBook eventBookFromFile = dataFromFile.toModelType();
        EventBook typicalEventsEventBook = TypicalEvents.getTypicalEventBook();
        assertEquals(eventBookFromFile, typicalEventsEventBook);
    }

    @Test
    public void toModelType_invalidEventFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(INVALID_EVENT_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEvents_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EVENT_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventBook.MESSAGE_DUPLICATE_EVENT,
                dataFromFile::toModelType);
    }

}
