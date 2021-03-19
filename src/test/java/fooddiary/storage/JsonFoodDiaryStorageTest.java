package fooddiary.storage;

import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalPersons.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonFoodDiaryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyFoodDiary> readAddressBook(String filePath) throws Exception {
        return new JsonFoodDiaryStorage(Paths.get(filePath)).readFoodDiary(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        FoodDiary original = getTypicalAddressBook();
        JsonFoodDiaryStorage jsonAddressBookStorage = new JsonFoodDiaryStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveFoodDiary(original, filePath);
        ReadOnlyFoodDiary readBack = jsonAddressBookStorage.readFoodDiary(filePath).get();
        assertEquals(original, new FoodDiary(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEntry(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveFoodDiary(original, filePath);
        readBack = jsonAddressBookStorage.readFoodDiary(filePath).get();
        assertEquals(original, new FoodDiary(readBack));

        // Save and read without specifying file path
        original.addEntry(IDA);
        jsonAddressBookStorage.saveFoodDiary(original); // file path not specified
        readBack = jsonAddressBookStorage.readFoodDiary().get(); // file path not specified
        assertEquals(original, new FoodDiary(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyFoodDiary addressBook, String filePath) {
        try {
            new JsonFoodDiaryStorage(Paths.get(filePath))
                    .saveFoodDiary(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new FoodDiary(), null));
    }
}
