package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_RESIDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalResidentsAddressBook.json");
    private static final Path INVALID_RESIDENT_FILE = TEST_DATA_FOLDER.resolve("invalidResidentAddressBook.json");
    private static final Path DUPLICATE_RESIDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateResidentAddressBook.json");

    @Test
    public void toModelType_typicalResidentsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_RESIDENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalResidentsAddressBook = getTypicalAddressBook();
        typicalResidentsAddressBook.equals(addressBookFromFile);
        assertEquals(addressBookFromFile, typicalResidentsAddressBook);
    }

    @Test
    public void toModelType_invalidResidentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_RESIDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateResidents_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_RESIDENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_RESIDENT,
                dataFromFile::toModelType);
    }

}
