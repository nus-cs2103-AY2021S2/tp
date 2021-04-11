package seedu.flashback.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashback.commons.exceptions.DataConversionException;
import seedu.flashback.model.FlashBack;
import seedu.flashback.model.ReadOnlyFlashBack;

/**
 * Represents a storage for {@link FlashBack}.
 */
public interface FlashBackStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFlashBackFilePath();

    /**
     * Returns FlashBack data as a {@link ReadOnlyFlashBack}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFlashBack> readFlashBack() throws DataConversionException, IOException;

    /**
     * @see #getFlashBackFilePath()
     */
    Optional<ReadOnlyFlashBack> readFlashBack(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFlashBack} to the storage.
     * @param flashBack cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFlashBack(ReadOnlyFlashBack flashBack) throws IOException;

    /**
     * @see #saveFlashBack(ReadOnlyFlashBack)
     */
    void saveFlashBack(ReadOnlyFlashBack flashBack, Path filePath) throws IOException;

}
