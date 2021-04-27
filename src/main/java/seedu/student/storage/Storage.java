package seedu.student.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.student.commons.exceptions.DataConversionException;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.ReadOnlyUserPrefs;
import seedu.student.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StudentBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentBookFilePath();

    @Override
    Optional<ReadOnlyStudentBook> readStudentBook() throws DataConversionException, IOException;

    @Override
    void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException;

}
