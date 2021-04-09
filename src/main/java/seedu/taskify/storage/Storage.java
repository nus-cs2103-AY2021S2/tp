package seedu.taskify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taskify.commons.exceptions.DataConversionException;
import seedu.taskify.model.ReadOnlyTaskify;
import seedu.taskify.model.ReadOnlyUserPrefs;
import seedu.taskify.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskifyStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskifyFilePath();

    @Override
    Optional<ReadOnlyTaskify> readTaskifyData() throws DataConversionException, IOException;

    @Override
    void saveTaskifyData(ReadOnlyTaskify taskify) throws IOException;

}
