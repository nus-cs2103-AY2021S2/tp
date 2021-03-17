package fooddiary.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static fooddiary.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import fooddiary.commons.util.JsonUtil;
import org.junit.jupiter.api.Test;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.FoodDiary;
import fooddiary.testutil.TypicalPersons;

public class JsonSerializableFoodDiaryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableAddressBook.class).get();
        FoodDiary foodDiaryFromFile = dataFromFile.toModelType();
        FoodDiary typicalPersonsFoodDiary = TypicalPersons.getTypicalAddressBook();
        assertEquals(foodDiaryFromFile, typicalPersonsFoodDiary);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
