package seedu.dictionote.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dictionote.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.commons.util.JsonUtil;
import seedu.dictionote.model.ContactsList;
import seedu.dictionote.testutil.TypicalContacts;

public class JsonSerializableContactsListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalContactsList.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidContactsList.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateContactsList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableContactsList dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableContactsList.class).get();
        ContactsList contactsListFromFile = dataFromFile.toModelType();
        ContactsList typicalPersonsContactsList = TypicalContacts.getTypicalContactsList();
        assertEquals(contactsListFromFile, typicalPersonsContactsList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableContactsList dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableContactsList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableContactsList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableContactsList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableContactsList.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
