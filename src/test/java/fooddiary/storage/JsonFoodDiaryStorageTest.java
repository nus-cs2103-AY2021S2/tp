package fooddiary.storage;

import static fooddiary.testutil.Assert.assertThrows;
import static fooddiary.testutil.TypicalEntries.ENTRY_A;
import static fooddiary.testutil.TypicalEntries.ENTRY_H;
import static fooddiary.testutil.TypicalEntries.ENTRY_I;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;


public class JsonFoodDiaryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFoodDiaryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFoodDiary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFoodDiary(null));
    }

    private java.util.Optional<ReadOnlyFoodDiary> readFoodDiary(String filePath) throws Exception {
        return new JsonFoodDiaryStorage(Paths.get(filePath)).readFoodDiary(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFoodDiary("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFoodDiary("notJsonFormatFoodDiary.json"));
    }

    @Test
    public void readFoodDiary_invalidEntryFoodDiary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFoodDiary("invalidEntryFoodDiary.json"));
    }

    @Test
    public void readFoodDiary_invalidAndValidEntryFoodDiary_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFoodDiary("invalidAndValidEntryFoodDiary.json"));
    }

    @Test
    public void readAndSaveFoodDiary_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFoodDiary.json");
        FoodDiary original = getTypicalFoodDiary();
        JsonFoodDiaryStorage jsonFoodDiaryStorage = new JsonFoodDiaryStorage(filePath);

        // Save in new file and read back
        jsonFoodDiaryStorage.saveFoodDiary(original, filePath);
        ReadOnlyFoodDiary readBack = jsonFoodDiaryStorage.readFoodDiary(filePath).get();
        assertEquals(original, new FoodDiary(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addEntry(ENTRY_H);
        original.removeEntry(ENTRY_A);
        jsonFoodDiaryStorage.saveFoodDiary(original, filePath);
        readBack = jsonFoodDiaryStorage.readFoodDiary(filePath).get();
        assertEquals(original, new FoodDiary(readBack));

        // Save and read without specifying file path
        original.addEntry(ENTRY_I);
        jsonFoodDiaryStorage.saveFoodDiary(original); // file path not specified
        readBack = jsonFoodDiaryStorage.readFoodDiary().get(); // file path not specified
        assertEquals(original, new FoodDiary(readBack));

    }

    @Test
    public void saveFoodDiary_nullFoodDiary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFoodDiary(null, "SomeFile.json"));
    }

    /**
     * Saves {@code foodDiary} at the specified {@code filePath}.
     */
    private void saveFoodDiary(ReadOnlyFoodDiary foodDiary, String filePath) {
        try {
            new JsonFoodDiaryStorage(Paths.get(filePath))
                    .saveFoodDiary(foodDiary, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFoodDiary_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFoodDiary(new FoodDiary(), null));
    }
}
