package seedu.dictionote.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.model.ReadOnlyDefinitionBook;

/**
 * Represents a storage for {@link seedu.dictionote.model.DefinitionBook}.
 */
public interface DefinitionBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDefinitionBookFilePath();

    /**
     * Returns Definition Book data as a {@link ReadOnlyDefinitionBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDefinitionBook> readDefinitionBook() throws DataConversionException, IOException;

    /**
     * @see #getDefinitionBookFilePath()
     */
    Optional<ReadOnlyDefinitionBook> readDefinitionBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDefinitionBook} to the storage.
     * @param definition cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDefinitionBook(ReadOnlyDefinitionBook definition) throws IOException;

    /**
     * @see #saveDefinitionBook(ReadOnlyDefinitionBook)
     */
    void saveDefinitionBook(ReadOnlyDefinitionBook definitionBook, Path filePath) throws IOException;

}
