package seedu.booking.storage;

import static seedu.booking.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.commons.util.JsonUtil;

public class JsonSerializableBookingSystemTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableBookingSystemTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsBookingSystem.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonBookingSystem.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonBookingSystem.json");


    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableBookingSystem dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableBookingSystem.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableBookingSystem dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableBookingSystem.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableBookingSystem.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
