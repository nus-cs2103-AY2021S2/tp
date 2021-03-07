package seedu.smartlib.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.smartlib.commons.exceptions.DataConversionException;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.ReadOnlyUserPrefs;
import seedu.smartlib.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SmartLibStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSmartLibFilePath();

    @Override
    Optional<ReadOnlySmartLib> readSmartLib() throws DataConversionException, IOException;

    @Override
    void saveSmartLib(ReadOnlySmartLib smartLib) throws IOException;

}
