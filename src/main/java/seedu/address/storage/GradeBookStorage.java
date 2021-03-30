package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGradeBook;

/**
 * Represents a storage for {@link seedu.address.model.GradeBook}
 */
public interface GradeBookStorage {

    /**
     * @return File path of Grade Book data file
     */
    Path getGradeBookFilePath();

    /**
     * Returns Grade book as a {@link ReadOnlyGradeBook}
     * @return {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException,
            IOException;

    /**
     * @param filePath File path of Grade book
     * @return {@code Optional.empty()} if filePath is an empty Grade Book
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyGradeBook> readGradeBook(Path filePath) throws DataConversionException,
            IOException;


    /**
     * Saves the given {@link ReadOnlyGradeBook} to the storage.
     * @param gradeBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGradeBook(ReadOnlyGradeBook gradeBook) throws IOException;

    /**
     * @see #saveGradeBook(ReadOnlyGradeBook)
     */
    void saveGradeBook(ReadOnlyGradeBook gradeBook, Path filePath) throws IOException;
}
