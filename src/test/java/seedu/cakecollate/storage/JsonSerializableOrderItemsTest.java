package seedu.cakecollate.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cakecollate.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.commons.util.JsonUtil;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.testutil.TypicalOrderItems;

public class JsonSerializableOrderItemsTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableOrderItemsTest");
    private static final Path TYPICAL_ORDER_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalOrderItems.json");
    private static final Path INVALID_ORDER_ITEMS_FILE = TEST_DATA_FOLDER.resolve("invalidOrderItems.json");
    private static final Path DUPLICATE_ORDER_ITEMS_FILE = TEST_DATA_FOLDER.resolve("duplicateOrderItems.json");

    @Test
    public void toModelType_typicalOrderItemsFile_success() throws Exception {
        JsonSerializableOrderItems dataFromFile = JsonUtil.readJsonFile(TYPICAL_ORDER_ITEMS_FILE,
                JsonSerializableOrderItems.class).get();
        OrderItems orderItemsFromFile = dataFromFile.toModelType();
        OrderItems typicalOrdersCakeCollate = TypicalOrderItems.getTypicalOrderItemsModel();
        assertEquals(orderItemsFromFile, typicalOrdersCakeCollate);
    }

    @Test
    public void toModelType_invalidOrderItemsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableOrderItems dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_ITEMS_FILE,
                JsonSerializableOrderItems.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateOrderItems_throwsIllegalValueException() throws Exception {
        JsonSerializableOrderItems dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORDER_ITEMS_FILE,
                JsonSerializableOrderItems.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableOrderItems.MESSAGE_DUPLICATE_ORDER_ITEMS,
                dataFromFile::toModelType);
    }
}
