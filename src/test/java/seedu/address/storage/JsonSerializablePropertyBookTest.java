package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.PropertyBook;
import seedu.address.testutil.TypicalProperties;

public class JsonSerializablePropertyBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializablePropertyBookTest");
    private static final Path TYPICAL_PROPERTIES_FILE = TEST_DATA_FOLDER
            .resolve("typicalPropertiesPropertyBook.json");
    private static final Path INVALID_PROPERTY_FILE = TEST_DATA_FOLDER
            .resolve("invalidPropertyPropertyBook.json");
    private static final Path DUPLICATE_PROPERTY_FILE = TEST_DATA_FOLDER
            .resolve("duplicatePropertyPropertyBook.json");

    @Test
    public void toModelType_typicalPropertiesFile_success() throws Exception {
        JsonSerializablePropertyBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROPERTIES_FILE,
                JsonSerializablePropertyBook.class).get();
        PropertyBook propertyBookFromFile = dataFromFile.toModelType();
        PropertyBook typicalPropertiesPropertyBook = TypicalProperties.getTypicalPropertyBook();
        assertEquals(propertyBookFromFile, typicalPropertiesPropertyBook);
    }

    @Test
    public void toModelType_invalidPropertyFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyBook dataFromFile = JsonUtil.readJsonFile(INVALID_PROPERTY_FILE,
                JsonSerializablePropertyBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProperties_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROPERTY_FILE,
                JsonSerializablePropertyBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePropertyBook.MESSAGE_DUPLICATE_PROPERTY,
                dataFromFile::toModelType);
    }
}

