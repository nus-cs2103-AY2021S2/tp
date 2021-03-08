package seedu.storemando.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.storemando.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.commons.util.JsonUtil;
import seedu.storemando.model.StoreMando;
import seedu.storemando.testutil.TypicalItems;

public class JsonSerializableStoreMandoTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStoreMandoTest");
    private static final Path TYPICAL_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalItemsStoreMando.json");
    private static final Path INVALID_ITEM_FILE = TEST_DATA_FOLDER.resolve("invalidItemStoreMando.json");
    private static final Path DUPLICATE_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateItemStoreMando.json");

    @Test
    public void toModelType_typicalItemsFile_success() throws Exception {
        JsonSerializableStoreMando dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITEMS_FILE,
            JsonSerializableStoreMando.class).get();
        StoreMando storeMandoFromFile = dataFromFile.toModelType();
        StoreMando typicalItemsStoreMando = TypicalItems.getTypicalStoreMando();
        assertEquals(storeMandoFromFile, typicalItemsStoreMando);
    }

    @Test
    public void toModelType_invalidItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStoreMando dataFromFile = JsonUtil.readJsonFile(INVALID_ITEM_FILE,
            JsonSerializableStoreMando.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItems_throwsIllegalValueException() throws Exception {
        JsonSerializableStoreMando dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITEM_FILE,
            JsonSerializableStoreMando.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStoreMando.MESSAGE_DUPLICATE_ITEM,
            dataFromFile::toModelType);
    }

}
