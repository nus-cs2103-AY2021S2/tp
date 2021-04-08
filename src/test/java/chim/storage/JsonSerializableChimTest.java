package chim.storage;

import static chim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import chim.commons.exceptions.IllegalValueException;
import chim.commons.util.JsonUtil;
import chim.model.Chim;
import chim.testutil.TypicalModels;

public class JsonSerializableChimTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableChimTest");
    private static final Path TYPICAL_CUSTOMERS_FILE = TEST_DATA_FOLDER.resolve("typicalChim.json");
    private static final Path INVALID_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("invalidCustomerChim.json");
    private static final Path DUPLICATE_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("duplicateCustomerChim.json");

    @Test
    public void toModelType_typicalFile_success() throws Exception {
        JsonSerializableChim dataFromFile = JsonUtil.readJsonFile(TYPICAL_CUSTOMERS_FILE,
                JsonSerializableChim.class).get();
        Chim chimFromFile = dataFromFile.toModelType();
        Chim typicalCustomersChim = TypicalModels.getTypicalChim();
        assertEquals(chimFromFile, typicalCustomersChim);
    }

    @Test
    public void toModelType_invalidCustomerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableChim dataFromFile = JsonUtil.readJsonFile(INVALID_CUSTOMER_FILE,
                JsonSerializableChim.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCustomers_throwsIllegalValueException() throws Exception {
        JsonSerializableChim dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CUSTOMER_FILE,
                JsonSerializableChim.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableChim.MESSAGE_DUPLICATE_CUSTOMER,
                dataFromFile::toModelType);
    }

}
