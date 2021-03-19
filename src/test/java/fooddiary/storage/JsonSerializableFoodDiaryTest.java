package fooddiary.storage;

import static fooddiary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.commons.util.JsonUtil;
import fooddiary.model.FoodDiary;
import fooddiary.testutil.TypicalEntries;

public class JsonSerializableFoodDiaryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFoodDiaryTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalEntriesFoodDiary.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidEntryFoodDiary.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateEntryFoodDiary.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFoodDiary dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFoodDiary.class).get();
        FoodDiary foodDiaryFromFile = dataFromFile.toModelType();
        FoodDiary typicalPersonsFoodDiary = TypicalEntries.getTypicalFoodDiary();
        assertEquals(foodDiaryFromFile, typicalPersonsFoodDiary);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodDiary dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFoodDiary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodDiary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFoodDiary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFoodDiary.MESSAGE_DUPLICATE_FOOD_DIARY,
                dataFromFile::toModelType);
    }

}
