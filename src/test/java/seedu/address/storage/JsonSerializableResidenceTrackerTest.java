package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ResidenceTracker;
import seedu.address.testutil.TypicalResidences;

public class JsonSerializableResidenceTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_RESIDENCE_FILE = TEST_DATA_FOLDER
            .resolve("typicalResidencesResidenceTracker.json");
    private static final Path INVALID_RESIDENCE_FILE = TEST_DATA_FOLDER
            .resolve("invalidResidenceResidenceTracker.json");
    private static final Path DUPLICATE_PERSON_FILE =
            TEST_DATA_FOLDER.resolve("duplicateResidenceResidenceTracker.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableResidenceTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_RESIDENCE_FILE,
                JsonSerializableResidenceTracker.class).get();
        ResidenceTracker addressBookFromFile = dataFromFile.toModelType();
        ResidenceTracker typicalPersonsAddressBook = TypicalResidences.getTypicalResidenceTracker();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableResidenceTracker dataFromFile = JsonUtil.readJsonFile(INVALID_RESIDENCE_FILE,
                JsonSerializableResidenceTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableResidenceTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableResidenceTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableResidenceTracker.MESSAGE_DUPLICATE_RESIDENCE,
                dataFromFile::toModelType);
    }

}
