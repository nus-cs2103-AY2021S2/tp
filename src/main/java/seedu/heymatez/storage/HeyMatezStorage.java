package seedu.heymatez.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.heymatez.commons.exceptions.DataConversionException;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.ReadOnlyHeyMatez;

/**
 * Represents a storage for {@link HeyMatez}.
 */
public interface HeyMatezStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHeyMatezFilePath();

    /**
     * Returns HeyMatez data as a {@link ReadOnlyHeyMatez}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHeyMatez> readHeyMatez() throws DataConversionException, IOException;

    /**
     * @see #getHeyMatezFilePath()
     */
    Optional<ReadOnlyHeyMatez> readHeyMatez(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHeyMatez} to the storage.
     * @param heyMatez cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHeyMatez(ReadOnlyHeyMatez heyMatez) throws IOException;

    /**
     * @see #saveHeyMatez(ReadOnlyHeyMatez)
     */
    void saveHeyMatez(ReadOnlyHeyMatez heyMatez, Path filePath) throws IOException;
}
