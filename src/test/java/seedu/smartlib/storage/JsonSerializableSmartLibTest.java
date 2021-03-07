package seedu.smartlib.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.exceptions.IllegalValueException;
import seedu.smartlib.commons.util.JsonUtil;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.testutil.TypicalPersons;

public class JsonSerializableSmartLibTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalReaderSmartLib.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidReaderSmartLib.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateReaderSmartLib.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSmartLib dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableSmartLib.class).get();
        SmartLib smartLibFromFile = dataFromFile.toModelType();
        SmartLib typicalPersonsSmartLib = TypicalPersons.getTypicalAddressBook();
        assertEquals(smartLibFromFile, typicalPersonsSmartLib);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSmartLib dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableSmartLib.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSmartLib dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableSmartLib.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSmartLib.MESSAGE_DUPLICATE_READER,
                dataFromFile::toModelType);
    }

}
