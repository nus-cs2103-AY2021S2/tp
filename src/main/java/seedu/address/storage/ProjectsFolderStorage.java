package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyProjectsFolder;

/**
 * Represents a storage for {@link seedu.address.model.ProjectsFolder}.
 */
public interface ProjectsFolderStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getProjectsFolderFilePath();

    /**
     * Returns Projects Folder data as a {@link ReadOnlyProjectsFolder}.
     *
     * @return A {@link ReadOnlyProjectsFolder} or {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyProjectsFolder> readProjectsFolder() throws DataConversionException, IOException;

    /**
     * @see #getProjectsFolderFilePath()
     */
    Optional<ReadOnlyProjectsFolder> readProjectsFolder(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyProjectsFolder} to the storage.
     *
     * @param projectsFolder cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProjectsFolder(ReadOnlyProjectsFolder projectsFolder) throws IOException;

    /**
     * @see #saveProjectsFolder(ReadOnlyProjectsFolder)
     */
    void saveProjectsFolder(ReadOnlyProjectsFolder projectsFolder, Path filePath) throws IOException;

}
