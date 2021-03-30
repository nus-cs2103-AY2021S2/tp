package seedu.dictionote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.Dictionary;
import seedu.dictionote.model.ReadOnlyDictionary;

/**
 * Represents a storage for {@link Dictionary}.
 */
public interface DictionaryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDictionaryFilePath();

    /**
     * Returns Dictionary data as a {@link ReadOnlyDictionary}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDictionary> readDictionary() throws DataConversionException, IOException;

    /**
     * @see #getDictionaryFilePath()
     */
    Optional<ReadOnlyDictionary> readDictionary(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDictionary} to the storage.
     * @param dictionary cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDictionary(ReadOnlyDictionary dictionary) throws IOException;

    /**
     * @see #saveDictionary(ReadOnlyDictionary)
     */
    void saveDictionary(ReadOnlyDictionary dictionary, Path filePath) throws IOException;

}
