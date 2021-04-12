package seedu.ta.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ta.commons.exceptions.DataConversionException;
import seedu.ta.model.ReadOnlyTeachingAssistant;
import seedu.ta.model.ReadOnlyUserPrefs;
import seedu.ta.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TeachingAssistantStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTeachingAssistantFilePath();

    @Override
    Optional<ReadOnlyTeachingAssistant> readTeachingAssistant() throws DataConversionException, IOException;

    @Override
    void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant) throws IOException;

}
