package seedu.weeblingo.storage;

import org.junit.jupiter.api.Test;
import seedu.weeblingo.commons.exceptions.IllegalValueException;
import seedu.weeblingo.commons.util.JsonUtil;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.testutil.TypicalFlashcards;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static seedu.weeblingo.testutil.Assert.assertThrows;

public class JsonSerializableFlashcardBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonSerializableFlashcardBookTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardBook.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardBook.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardBook1.json");
    private static final Path DUPLICATE_SCORE_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardBook2.json");

    @Test
    public void toModelType_typicalFlashcardsFile_success() throws Exception {
        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
                JsonSerializableFlashcardBook.class).get();
                FlashcardBook flashcardBookFromFile = dataFromFile.toModelType();
                FlashcardBook typicalFlashcardBook = TypicalFlashcards.getTypicalFlashcardBook();
                assertEquals(flashcardBookFromFile, typicalFlashcardBook);
    }

    @Test
    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
        JsonSerializableFlashcardBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableFlashcardBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashcardBook.MESSAGE_DUPLICATE_FLASHCARD,
        dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateScores_throwsIllegalValueException() throws Exception {
        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SCORE_FILE,
                JsonSerializableFlashcardBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFlashcardBook.MESSAGE_DUPLICATE_SCORE,
                dataFromFile::toModelType);
    }
}
