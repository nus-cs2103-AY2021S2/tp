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
    private static final Path TYPICAL_ENTRIES_FILE = TEST_DATA_FOLDER.resolve("typicalEntriesFoodDiary.json");
    private static final Path INVALID_ENTRY_FILE = TEST_DATA_FOLDER.resolve("invalidEntryFoodDiary.json");
    private static final Path DUPLICATE_ENTRY_FILE = TEST_DATA_FOLDER.resolve("duplicateEntryFoodDiary.json");

    @Test
    public void toModelType_typicalEntriesFile_success() throws Exception {
        JsonSerializableFoodDiary dataFromFile = JsonUtil.readJsonFile(TYPICAL_ENTRIES_FILE,
                JsonSerializableFoodDiary.class).get();
        FoodDiary foodDiaryFromFile = dataFromFile.toModelType();
        FoodDiary typicalEntriesFoodDiary = TypicalEntries.getTypicalFoodDiary();
        assertEquals(foodDiaryFromFile, typicalEntriesFoodDiary);
    }

    @Test
    public void toModelType_invalidEntryFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodDiary dataFromFile = JsonUtil.readJsonFile(INVALID_ENTRY_FILE,
                JsonSerializableFoodDiary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEntries_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodDiary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENTRY_FILE,
                JsonSerializableFoodDiary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFoodDiary.MESSAGE_DUPLICATE_FOOD_DIARY,
                dataFromFile::toModelType);
    }

}
