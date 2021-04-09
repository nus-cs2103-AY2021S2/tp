package seedu.taskify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taskify.commons.exceptions.DataConversionException;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.Taskify;

/**
 * Represents a storage for {@link Taskify}.
 */
public interface TaskifyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskifyFilePath();

    /**
     * Returns TaskifyParser data as a {@link ReadOnlyTaskify}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskify> readTaskifyData() throws DataConversionException, IOException;

    /**
     * @see #getTaskifyFilePath()
     */
    Optional<ReadOnlyTaskify> readTaskifyData(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskify} to the storage.
     * @param taskify cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskifyData(ReadOnlyTaskify taskify) throws IOException;

    /**
     * @see #saveTaskifyData(ReadOnlyTaskify)
     */
    void saveTaskifyData(ReadOnlyTaskify taskify, Path filePath) throws IOException;

}
