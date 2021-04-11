package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalTeachingAssistant;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path INVALID_CONTACT_FILE = TEST_DATA_FOLDER.resolve("invalidContactTeachingAssistant.json");
    private static final Path DUPLICATE_CONTACT_FILE = TEST_DATA_FOLDER.resolve(
                                                                    "duplicateContactTeachingAssistant.json");
    private static final Path TYPICAL_TEACHING_ASSISTANT_FILE = TEST_DATA_FOLDER.resolve(
                                                                    "typicalTeachingAssistant.json");
    private static final Path OVERLAPPING_ENTRY_FILE = TEST_DATA_FOLDER.resolve(
                                                                    "overlappingEntryTeachingAssistant.json");
    @Test
    public void toModelType_typicalTeachingAssistantFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_TEACHING_ASSISTANT_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalTeachingAssistant = TypicalTeachingAssistant.getTypicalTeachingAssistant();
        assertEquals(addressBookFromFile, typicalTeachingAssistant);
    }

    @Test
    public void toModelType_overlappingEntries_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(OVERLAPPING_ENTRY_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_OVERLAPPING_ENTRY,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateContacts_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CONTACT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_CONTACT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidContact_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_CONTACT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
