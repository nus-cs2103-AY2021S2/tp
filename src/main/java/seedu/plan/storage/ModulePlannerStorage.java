package seedu.plan.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.plan.commons.exceptions.DataConversionException;
import seedu.plan.model.ModulePlanner;
import seedu.plan.model.ReadOnlyModulePlanner;

/**
 * Represents a storage for {@link ModulePlanner}.
 */
public interface ModulePlannerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPlansFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyModulePlanner}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModulePlanner> readPlans() throws DataConversionException, IOException;

    /**
     * @see #getPlansFilePath()
     */
    Optional<ReadOnlyModulePlanner> readPlans(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModulePlanner} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePlans(ReadOnlyModulePlanner addressBook) throws IOException;

    /**
     * @see #savePlans(ReadOnlyModulePlanner)
     */
    void savePlans(ReadOnlyModulePlanner addressBook, Path filePath) throws IOException;

}
