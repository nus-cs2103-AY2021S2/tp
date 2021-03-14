package seedu.iscam.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.iscam.commons.exceptions.DataConversionException;
import seedu.iscam.model.ReadOnlyClientBook;
import seedu.iscam.model.ReadOnlyUserPrefs;
import seedu.iscam.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ClientBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClientBookFilePath();

    @Override
    Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException, IOException;

    @Override
    void saveClientBook(ReadOnlyClientBook clientBook) throws IOException;

}
