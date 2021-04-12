package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyDatesBook;

/**
 * Represents a storage for {@link seedu.address.model.DatesBook}.
 */
public interface DatesBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDatesBookFilePath();

    /**
     * Returns DatesBook data as a {@link ReadOnlyDatesBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDatesBook> readDatesBook() throws DataConversionException, IOException;

    /**
     * @see #getDatesBookFilePath()
     */
    Optional<ReadOnlyDatesBook> readDatesBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDatesBook} to the storage.
     *
     * @param datesBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDatesBook(ReadOnlyDatesBook datesBook) throws IOException;

    /**
     * @see #saveDatesBook(ReadOnlyDatesBook)
     */
    void saveDatesBook(ReadOnlyDatesBook datesBook, Path filePath) throws IOException;
}
