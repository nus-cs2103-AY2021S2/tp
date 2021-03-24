package seedu.weeblingo.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSerializableFlashcardBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcardsAddressBook.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcardAddressBook.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcardAddressBook.json");

    //    @Test
    //    public void toModelType_typicalFlashcardsFile_success() throws Exception {
    //        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
    //                JsonSerializableAddressBook.class).get();
    //        AddressBook addressBookFromFile = dataFromFile.toModelType();
    //        AddressBook typicalFlashcardsAddressBook = TypicalFlashcards.getTypicalAddressBook();
    //        assertEquals(addressBookFromFile, typicalFlashcardsAddressBook);
    //    }

    //    @Test
    //    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
    //        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
    //                JsonSerializableAddressBook.class).get();
    //        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    //    }

    //    @Test
    //    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
    //        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
    //                JsonSerializableAddressBook.class).get();
    //        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_FLASHCARD,
    //                dataFromFile::toModelType);
    //    }

}
