package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalItems;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalItemsAddressBook.json");
    private static final Path INVALID_ITEM_FILE = TEST_DATA_FOLDER.resolve("invalidItemAddressBook.json");
    private static final Path DUPLICATE_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateItemAddressBook.json");

    @Test
    public void toModelType_typicalItemsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITEMS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalItemsAddressBook = TypicalItems.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalItemsAddressBook);
    }

    @Test
    public void toModelType_invalidItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_ITEM_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItems_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITEM_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ITEM,
                dataFromFile::toModelType);
    }

}
