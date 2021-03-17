package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CakeCollate;
import seedu.address.testutil.TypicalOrders;

public class JsonSerializableCakeCollateTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCakeCollateTest");
    private static final Path TYPICAL_ORDERS_FILE = TEST_DATA_FOLDER.resolve("typicalOrdersCakeCollate.json");
    private static final Path INVALID_ORDER_FILE = TEST_DATA_FOLDER.resolve("invalidOrderCakeCollate.json");
    private static final Path DUPLICATE_ORDER_FILE = TEST_DATA_FOLDER.resolve("duplicateOrderCakeCollate.json");

    @Test
    public void toModelType_typicalOrdersFile_success() throws Exception {
        JsonSerializableCakeCollate dataFromFile = JsonUtil.readJsonFile(TYPICAL_ORDERS_FILE,
                JsonSerializableCakeCollate.class).get();
        CakeCollate cakeCollateFromFile = dataFromFile.toModelType();
        CakeCollate typicalOrdersCakeCollate = TypicalOrders.getTypicalCakeCollate();
        assertEquals(cakeCollateFromFile, typicalOrdersCakeCollate);
    }

    @Test
    public void toModelType_invalidOrderFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCakeCollate dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE,
                JsonSerializableCakeCollate.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateOrders_throwsIllegalValueException() throws Exception {
        JsonSerializableCakeCollate dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORDER_FILE,
                JsonSerializableCakeCollate.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCakeCollate.MESSAGE_DUPLICATE_ORDER,
                dataFromFile::toModelType);
    }

}
