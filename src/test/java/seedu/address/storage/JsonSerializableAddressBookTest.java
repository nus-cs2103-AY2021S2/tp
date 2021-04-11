package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalPassengers;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PASSENGERS_FILE = TEST_DATA_FOLDER.resolve("typicalPassengersAddressBook.json");
    private static final Path INVALID_PASSENGER_FILE = TEST_DATA_FOLDER.resolve("invalidPassengerAddressBook.json");
    private static final Path DUPLICATE_PASSENGER_FILE =
            TEST_DATA_FOLDER.resolve("duplicatePassengersAddressBook.json");

    @Test
    public void toModelType_typicalPassengersFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PASSENGERS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPassengersAddressBook = TypicalPassengers.getTypicalAddressBookPassengers();
        assertEquals(addressBookFromFile, typicalPassengersAddressBook);
    }

    @Test
    public void toModelType_invalidPassengerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PASSENGER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePassengers_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PASSENGER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PASSENGER,
                dataFromFile::toModelType);
    }

}
