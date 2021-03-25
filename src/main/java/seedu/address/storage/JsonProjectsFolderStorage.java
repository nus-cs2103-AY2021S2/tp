package seedu.address.storage;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyProjectsFolder;

/**
 * A class to access ProjectsFolder data stored as a json file on the hard disk.
 */
public class JsonProjectsFolderStorage implements ProjectsFolderStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProjectsFolderStorage.class);

    private Path filePath;

    public JsonProjectsFolderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProjectsFolderFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProjectsFolder> readProjectsFolder() throws DataConversionException {
        return readProjectsFolder(filePath);
    }

    /**
     * Similar to {@link #readProjectsFolder()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProjectsFolder> readProjectsFolder(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableProjectsFolder> jsonProjectsFolder = JsonUtil.readJsonFile(
                filePath, JsonSerializableProjectsFolder.class);
        if (!jsonProjectsFolder.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonProjectsFolder.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveProjectsFolder(ReadOnlyProjectsFolder projectsFolder) throws IOException {
        saveProjectsFolder(projectsFolder, filePath);
    }

    /**
     * Similar to {@link #saveProjectsFolder(ReadOnlyProjectsFolder)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveProjectsFolder(ReadOnlyProjectsFolder projectsFolder, Path filePath) throws IOException {
        requireAllNonNull(projectsFolder, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProjectsFolder(projectsFolder), filePath);
    }

}
