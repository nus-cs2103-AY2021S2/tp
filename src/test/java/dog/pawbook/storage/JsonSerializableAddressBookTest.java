package dog.pawbook.storage;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.commons.util.JsonUtil;
import dog.pawbook.model.AddressBook;
import dog.pawbook.testutil.TypicalOwners;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_OWNERS_FILE = TEST_DATA_FOLDER.resolve("typicalOwnersAddressBook.json");
    private static final Path INVALID_OWNER_FILE = TEST_DATA_FOLDER.resolve("invalidOwnerAddressBook.json");
    private static final Path DUPLICATE_OWNER_FILE = TEST_DATA_FOLDER.resolve("duplicateOwnerAddressBook.json");

    @Test
    public void toModelType_typicalOwnersFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_OWNERS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalOwnersAddressBook = TypicalOwners.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalOwnersAddressBook);
    }

    @Test
    public void toModelType_invalidOwnerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_OWNER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
