package seedu.us.among.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.us.among.commons.exceptions.DataConversionException;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.ReadOnlyEndpointList;

/**
 * Represents a storage for {@link EndpointList}.
 */
public interface EndpointListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEndpointListFilePath();

    /**
     * Returns EndpointList data as a {@link ReadOnlyEndpointList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEndpointList> readEndpointList() throws DataConversionException, IOException;

    /**
     * @see #getEndpointListFilePath()
     */
    Optional<ReadOnlyEndpointList> readEndpointList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEndpointList} to the storage.
     * @param endpointList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEndpointList(ReadOnlyEndpointList endpointList) throws IOException;

    /**
     * @see #saveEndpointList(ReadOnlyEndpointList)
     */
    void saveEndpointList(ReadOnlyEndpointList endpointList, Path filePath) throws IOException;

}
