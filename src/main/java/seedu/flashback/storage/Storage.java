package seedu.flashback.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashback.commons.exceptions.DataConversionException;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.ReadOnlyUserPrefs;
import seedu.flashback.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FlashBackStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFlashBackFilePath();

    @Override
    Optional<ReadOnlyFlashBack> readFlashBack() throws DataConversionException, IOException;

    @Override
    void saveFlashBack(ReadOnlyFlashBack flashBack) throws IOException;

}
