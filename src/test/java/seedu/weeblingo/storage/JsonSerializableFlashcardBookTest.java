package seedu.weeblingo.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableFlashcardBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonSerializableFlashcardBookTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardsBook.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardBook.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardBook.json");

    //    @Test
    //    public void toModelType_typicalFlashcardsFile_success() throws Exception {
    //        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
    //                JsonSerializableFlashcardBook.class).get();
    //        FlashcardBook addressBookFromFile = dataFromFile.toModelType();
    //        FlashcardBook typicalFlashcardsAddressBook = TypicalFlashcards.getTypicalAddressBook();
    //        assertEquals(addressBookFromFile, typicalFlashcardsAddressBook);
    //    }

    //    @Test
    //    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
    //        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
    //                JsonSerializableFlashcardBook.class).get();
    //        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    //    }

    //    @Test
    //    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
    //        JsonSerializableFlashcardBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
    //                JsonSerializableFlashcardBook.class).get();
    //        assertThrows(IllegalValueException.class, JsonSerializableFlashcardBook.MESSAGE_DUPLICATE_FLASHCARD,
    //                dataFromFile::toModelType);
    //    }

}
