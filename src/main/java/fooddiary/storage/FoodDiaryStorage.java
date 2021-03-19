package fooddiary.storage;

import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.model.FoodDiary;
import fooddiary.model.ReadOnlyFoodDiary;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link FoodDiary}.
 */
public interface FoodDiaryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodDiaryFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFoodDiary}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodDiary> readFoodDiary() throws DataConversionException, IOException;

    /**
     * @see #getFoodDiaryFilePath()
     */
    Optional<ReadOnlyFoodDiary> readFoodDiary(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodDiary} to the storage.
     *
     * @param foodDiary cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodDiary(ReadOnlyFoodDiary foodDiary) throws IOException;

    /**
     * @see #saveFoodDiary(ReadOnlyFoodDiary)
     */
    void saveFoodDiary(ReadOnlyFoodDiary foodDiary, Path filePath) throws IOException;

}
