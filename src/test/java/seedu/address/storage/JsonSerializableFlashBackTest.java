package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.FlashBack;
import seedu.address.testutil.TypicalFlashcards;

public class JsonSerializableFlashBackTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardFlashBack.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardFlashBack.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardFlashBack.json");

    @Test
    public void toModelType_typicalFlashcardsFile_success() throws Exception {
        JsonSerializableFlashBack dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFlashBack.class).get();
        FlashBack flashBackFromFile = dataFromFile.toModelType();
        FlashBack typicalFlashBack = TypicalFlashcards.getTypicalFlashBack();
        assertEquals(flashBackFromFile, typicalFlashBack);
    }

    //TODO: Might need to change invalidFlashcardFlashBack.java question field
    @Test
    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashBack dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFlashBack.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashBack dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFlashBack.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashBack.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
