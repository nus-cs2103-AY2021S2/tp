package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyColabFolder;

/**
 * A class to access ColabFolder data stored as a json file on the hard disk.
 */
public class JsonColabFolderStorage implements ColabFolderStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonColabFolderStorage.class);

    private Path filePath;

    public JsonColabFolderStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getColabFolderFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyColabFolder> readColabFolder() throws DataConversionException {
        return readColabFolder(filePath);
    }

    /**
     * Similar to {@link #readColabFolder()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyColabFolder> readColabFolder(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableColabFolder> jsonColabFolder = JsonUtil.readJsonFile(
                filePath, JsonSerializableColabFolder.class);
        if (!jsonColabFolder.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonColabFolder.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveColabFolder(ReadOnlyColabFolder colabFolder) throws IOException {
        saveColabFolder(colabFolder, filePath);
    }

    /**
     * Similar to {@link #saveColabFolder(ReadOnlyColabFolder)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveColabFolder(ReadOnlyColabFolder colabFolder, Path filePath) throws IOException {
        requireNonNull(colabFolder);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableColabFolder(colabFolder), filePath);
    }

}
