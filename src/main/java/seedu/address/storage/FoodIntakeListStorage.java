package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.food.FoodIntakeList;

/**
 * Represents a storage for a list of {@link seedu.address.model.food.FoodIntakeList}.
 */
public interface FoodIntakeListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodIntakeListFilePath();

    /**
     * Returns FoodIntakeList data as a {@link FoodIntakeList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<FoodIntakeList> readFoodIntakeList() throws DataConversionException, IOException;

    /**
     * @see #getFoodIntakeListFilePath()
     */
    Optional<FoodIntakeList> readFoodIntakeList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link FoodIntakeList} to the storage.
     * @param foodIntakeList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodIntakeList(FoodIntakeList foodIntakeList) throws IOException;

    /**
     * @see #saveFoodIntakeList(FoodIntakeList)
     */
    void saveFoodIntakeList(FoodIntakeList foodIntakeList, Path filePath) throws IOException;

}
