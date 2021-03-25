package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskTracker;
import seedu.address.model.TaskTracker;


/**
 * Represents a storage for {@link TaskTracker}.
 */
public interface TaskTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskTrackerFilePath();

    /**
     * Returns TaskTracker data as a {@link ReadOnlyTaskTracker}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskTracker> readTaskTracker() throws DataConversionException, IOException;


    /**
     * @see #getTaskTrackerFilePath()
     */
    Optional<ReadOnlyTaskTracker> readTaskTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskTracker} to the storage.
     *
     * @param taskTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskTracker(ReadOnlyTaskTracker taskTracker) throws IOException;

    /**
     * @see #saveTaskTracker(ReadOnlyTaskTracker)
     */
    void saveTaskTracker(ReadOnlyTaskTracker taskTracker, Path filePath) throws IOException;


}
