package seedu.dictionote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dictionote.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.commons.util.JsonUtil;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.testutil.TypicalContent;

public class JsonSerializableDictionaryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDictionaryTest");
    private static final Path TYPICAL_CONTENT_FILE = TEST_DATA_FOLDER.resolve("typicalContentDictionary.json");
    private static final Path INVALID_CONTENT_FILE = TEST_DATA_FOLDER.resolve("invalidContentDictionary.json");
    private static final Path DUPLICATE_CONTENT_FILE = TEST_DATA_FOLDER.resolve("duplicateContentDictionary.json");

    @Test
    public void toModelType_typicalContentFile_success() throws Exception {
        JsonSerializableDictionary dataFromFile = JsonUtil.readJsonFile(TYPICAL_CONTENT_FILE,
                JsonSerializableDictionary.class).get();
        Dictionary dictionaryFromFile = dataFromFile.toModelType();
        Dictionary typicalContentDictionary = TypicalContent.getTypicalDictionary();
        assertEquals(dictionaryFromFile, typicalContentDictionary);
    }

    @Test
    public void toModelType_invalidContentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDictionary dataFromFile = JsonUtil.readJsonFile(INVALID_CONTENT_FILE,
                JsonSerializableDictionary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContent_throwsIllegalValueException() throws Exception {
        JsonSerializableDictionary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTENT_FILE,
                JsonSerializableDictionary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDictionary.MESSAGE_DUPLICATE_CONTENT,
                dataFromFile::toModelType);
    }

}
