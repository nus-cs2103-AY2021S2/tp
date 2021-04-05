package seedu.heymatez.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.heymatez.commons.exceptions.DataConversionException;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.ReadOnlyUserPrefs;
import seedu.heymatez.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends HeyMatezStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getHeyMatezFilePath();

    @Override
    Optional<ReadOnlyHeyMatez> readHeyMatez() throws DataConversionException, IOException;

    @Override
    void saveHeyMatez(ReadOnlyHeyMatez heyMatez) throws IOException;

}
