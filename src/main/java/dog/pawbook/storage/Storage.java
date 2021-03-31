package dog.pawbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import dog.pawbook.commons.exceptions.DataConversionException;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.ReadOnlyUserPrefs;
import dog.pawbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends DatabaseStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDatabaseFilePath();

    @Override
    Optional<ReadOnlyDatabase> readDatabase() throws DataConversionException, IOException;

    @Override
    void saveDatabase(ReadOnlyDatabase database) throws IOException;

}
