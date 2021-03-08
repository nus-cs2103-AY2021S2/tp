package seedu.storemando.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.ReadOnlyUserPrefs;
import seedu.storemando.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StoreMandoStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStoreMandoFilePath();

    @Override
    Optional<ReadOnlyStoreMando> readStoreMando() throws DataConversionException, IOException;

    @Override
    void saveStoreMando(ReadOnlyStoreMando storeMando) throws IOException;

}
