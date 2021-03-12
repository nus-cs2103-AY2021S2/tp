package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyResidenceTracker;
import seedu.address.model.ResidenceTracker;

/**
 * Represents a storage for {@link ResidenceTracker}.
 */
public interface ResidenceTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getResidenceTrackerFilePath();

    /**
     * Returns ResidenceTracker data as a {@link ReadOnlyResidenceTracker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyResidenceTracker> readResidenceTracker() throws DataConversionException, IOException;

    /**
     * @see #getResidenceTrackerFilePath()
     */
    Optional<ReadOnlyResidenceTracker> readResidenceTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyResidenceTracker} to the storage.
     * @param residenceTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker) throws IOException;

    /**
     * @see #saveResidenceTracker(ReadOnlyResidenceTracker)
     */
    void saveResidenceTracker(ReadOnlyResidenceTracker residenceTracker, Path filePath) throws IOException;

}
