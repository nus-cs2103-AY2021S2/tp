package seedu.timeforwheels.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.timeforwheels.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.commons.exceptions.IllegalValueException;
import seedu.timeforwheels.commons.util.JsonUtil;
import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.testutil.TypicalCustomers;

public class JsonSerializableDeliveryListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDeliveryListTest");
    private static final Path TYPICAL_CUSTOMERS_FILE = TEST_DATA_FOLDER.resolve("typicalCustomersDeliveryList.json");
    private static final Path INVALID_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("invalidCustomerDeliveryList.json");
    private static final Path DUPLICATE_CUSTOMER_FILE = TEST_DATA_FOLDER.resolve("duplicateCustomerDeliveryList.json");

    @Test
    public void toModelType_typicalCustomersFile_success() throws Exception {
        JsonSerializableDeliveryList dataFromFile = JsonUtil.readJsonFile(TYPICAL_CUSTOMERS_FILE,
                JsonSerializableDeliveryList.class).get();
        DeliveryList deliveryListFromFile = dataFromFile.toModelType();
        DeliveryList typicalCustomersDeliveryList = TypicalCustomers.getTypicalDeliveryList();
        assertEquals(deliveryListFromFile, typicalCustomersDeliveryList);
    }

    @Test
    public void toModelType_invalidCustomerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDeliveryList dataFromFile = JsonUtil.readJsonFile(INVALID_CUSTOMER_FILE,
                JsonSerializableDeliveryList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCustomers_throwsIllegalValueException() throws Exception {
        JsonSerializableDeliveryList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CUSTOMER_FILE,
                JsonSerializableDeliveryList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDeliveryList.MESSAGE_DUPLICATE_CUSTOMER,
                dataFromFile::toModelType);
    }

}
