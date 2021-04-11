package seedu.flashback.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashback.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashback.commons.exceptions.IllegalValueException;
import seedu.flashback.commons.util.JsonUtil;
import seedu.flashback.model.FlashBack;
import seedu.flashback.testutil.TypicalFlashcards;

public class JsonSerializableFlashBackTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializableFlashBackTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardFlashBack.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardFlashBack.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardFlashBack.json");

    @Test
    public void toModelType_typicalFlashcardsFile_success() throws Exception {
        JsonSerializableFlashBack dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
                JsonSerializableFlashBack.class).get();
        FlashBack flashBackFromFile = dataFromFile.toModelType();
        FlashBack typicalFlashBack = TypicalFlashcards.getTypicalFlashBack();
        assertEquals(flashBackFromFile, typicalFlashBack);
    }

    //TODO: Might need to change invalidFlashcardFlashBack.java question field
    @Test
    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashBack dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableFlashBack.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashBack dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableFlashBack.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashBack.MESSAGE_DUPLICATE_CARD,
                dataFromFile::toModelType);
    }

}
