package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;

/**
 * Represents a storage for a list of {@link DietPlan}.
 */
public interface DietPlanListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDietPlanListFilePath();

    /**
     * Returns DietPlan data as a {@link DietPlanList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<DietPlanList> readDietPlanList() throws DataConversionException, IOException;

    /**
     * @see #getDietPlanListFilePath()
     */
    Optional<DietPlanList> readDietPlanList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link DietPlanList} to the storage.
     * @param dietPlanList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDietPlanList(DietPlanList dietPlanList) throws IOException;

    /**
     * @see #saveDietPlanList(DietPlanList)
     */
    void saveDietPlanList(DietPlanList dietPlanList, Path filePath) throws IOException;

}
