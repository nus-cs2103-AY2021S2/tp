package chim.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import chim.commons.exceptions.DataConversionException;
import chim.model.ReadOnlyChim;
import chim.model.ReadOnlyUserPrefs;
import chim.model.UserPrefs;

/**
 * API of the Storage component.
 */
public interface Storage extends ChimStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getChimFilePath();

    @Override
    Optional<ReadOnlyChim> readChim() throws DataConversionException, IOException;

    @Override
    void saveChim(ReadOnlyChim chim) throws IOException;

}
