package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.TutorBook;

/**
 * Represents a storage for {@link TutorBook}.
 */
public interface TutorBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTutorBookFilePath();

    /**
     * Returns TutorBook data as a {@link ReadOnlyTutorBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTutorBook> readTutorBook() throws DataConversionException, IOException;

    /**
     * @see #getTutorBookFilePath()
     */
    Optional<ReadOnlyTutorBook> readTutorBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTutorBook} to the storage.
     * @param tutorBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTutorBook(ReadOnlyTutorBook tutorBook) throws IOException;

    /**
     * @see #saveTutorBook(ReadOnlyTutorBook)
     */
    void saveTutorBook(ReadOnlyTutorBook tutorBook, Path filePath) throws IOException;

}
