package seedu.weeblingo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;

/**
 * Represents a storage for {@link FlashcardBook}.
 */
public interface FlashcardBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFlashcardBookFilePath();

    /**
     * Returns FlashcardBook data as a {@link ReadOnlyFlashcardBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFlashcardBook> readFlashcardBook() throws DataConversionException, IOException;

    /**
     * @see #getFlashcardBookFilePath()
     */
    Optional<ReadOnlyFlashcardBook> readFlashcardBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFlashcardBook} to the storage.
     * @param flashcardBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook) throws IOException;

    /**
     * @see #saveFlashcardBook(ReadOnlyFlashcardBook)
     */
    void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook, Path filePath) throws IOException;

}
