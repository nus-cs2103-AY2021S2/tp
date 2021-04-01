package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.food.UniqueFoodList;

/**
 * Represents a storage for a list of {@link seedu.address.model.food.Food}.
 */
public interface UniqueFoodListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodListFilePath();

    /**
     * Returns FoodList data as a {@link UniqueFoodList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UniqueFoodList> readFoodList() throws DataConversionException, IOException;

    /**
     * @see #getFoodListFilePath()
     */
    Optional<UniqueFoodList> readFoodList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link UniqueFoodList} to the storage.
     * @param foodList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodList(UniqueFoodList foodList) throws IOException;

    /**
     * @see #saveFoodList(UniqueFoodList)
     */
    void saveFoodList(UniqueFoodList foodList, Path filePath) throws IOException;

}
