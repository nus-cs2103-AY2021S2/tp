package seedu.plan.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.plan.commons.exceptions.DataConversionException;
import seedu.plan.model.ReadOnlyModulePlanner;
import seedu.plan.model.ReadOnlyUserPrefs;
import seedu.plan.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModulePlannerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPlansFilePath();

    @Override
    Optional<ReadOnlyModulePlanner> readPlans() throws DataConversionException, IOException;

    @Override
    void savePlans(ReadOnlyModulePlanner addressBook) throws IOException;

}
