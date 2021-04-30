package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalAddressBook;
import seedu.address.testutil.TypicalPassengers;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PASSENGERS_FILE = TEST_DATA_FOLDER.resolve("typicalPassengersAddressBook.json");
    private static final Path INVALID_PASSENGER_FILE = TEST_DATA_FOLDER.resolve("invalidPassengerAddressBook.json");
    private static final Path DUPLICATE_PASSENGER_FILE =
            TEST_DATA_FOLDER.resolve("duplicatePassengersAddressBook.json");
    private static final Path TYPICAL_ADDRESSBOOK_FILE = TEST_DATA_FOLDER.resolve("typicalAddressBook.json");
    private static final Path INVALID_POOL_FILE = TEST_DATA_FOLDER.resolve("invalidPoolAddressBook.json");
    private static final Path DUPLICATE_POOL_FILE = TEST_DATA_FOLDER.resolve("duplicatePoolsAddressBook.json");
    private static final Path DUPLICATE_PASSENGER_REFERENCE_FILE =
            TEST_DATA_FOLDER.resolve("duplicatePassengersReferenceAddressBook.json");
    private static final Path MISMATCH_TRIPDAY_FILE =
            TEST_DATA_FOLDER.resolve("mismatchTripDayAddressBook.json");

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

    @Test
    public void toModelType_typicalAddressBookFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ADDRESSBOOK_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPassengersAddressBook = TypicalAddressBook.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPassengersAddressBook);
    }

    // Pool reference nonexistent Passenger
    @Test
    public void toModelType_invalidPoolFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_POOL_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    // Two Pools with same identity
    @Test
    public void toModelType_duplicatePool_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_POOL_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_POOL,
                dataFromFile::toModelType);
    }

    // Two Pools referencing the same Passenger
    @Test
    public void toModelType_duplicatePassengerRef_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PASSENGER_REFERENCE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PASSENGER_REF,
                dataFromFile::toModelType);
    }

    // Pool and Passenger mismatch trip day
    @Test
    public void toModelType_mismatchPassengerTripDay_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(MISMATCH_TRIPDAY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_POOL_PASSENGER_DAY_MISMATCH,
                dataFromFile::toModelType);
    }
}
