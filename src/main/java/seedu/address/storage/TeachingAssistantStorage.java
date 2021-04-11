package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTeachingAssistant;

/**
 * Represents a storage for {@link seedu.address.model.TeachingAssistant}.
 */
public interface TeachingAssistantStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTeachingAssistantFilePath();

    /**
     * Returns TeachingAssistant data as a {@link ReadOnlyTeachingAssistant}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTeachingAssistant> readTeachingAssistant() throws DataConversionException, IOException;

    /**
     * @see #getTeachingAssistantFilePath()
     */
    Optional<ReadOnlyTeachingAssistant> readTeachingAssistant(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTeachingAssistant} to the storage.
     * @param teachingAssistant cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant) throws IOException;

    /**
     * @see #saveTeachingAssistant(ReadOnlyTeachingAssistant)
     */
    void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant, Path filePath) throws IOException;

}
