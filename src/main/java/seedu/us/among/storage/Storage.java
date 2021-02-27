package seedu.us.among.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.us.among.commons.exceptions.DataConversionException;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.ReadOnlyUserPrefs;
import seedu.us.among.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends EndpointListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getEndpointListFilePath();

    @Override
    Optional<ReadOnlyEndpointList> readEndpointList() throws DataConversionException, IOException;

    @Override
    void saveEndpointList(ReadOnlyEndpointList endpointList) throws IOException;

}
