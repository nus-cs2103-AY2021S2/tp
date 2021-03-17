package seedu.cakecollate.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.ReadOnlyUserPrefs;
import seedu.cakecollate.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends CakeCollateStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getCakeCollateFilePath();

    @Override
    Optional<ReadOnlyCakeCollate> readCakeCollate() throws DataConversionException, IOException;

    @Override
    void saveCakeCollate(ReadOnlyCakeCollate cakeCollate) throws IOException;

}
