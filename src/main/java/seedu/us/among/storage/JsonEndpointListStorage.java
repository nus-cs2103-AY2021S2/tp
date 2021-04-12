package seedu.us.among.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.exceptions.DataConversionException;
import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.commons.util.FileUtil;
import seedu.us.among.commons.util.JsonUtil;
import seedu.us.among.model.ReadOnlyEndpointList;

/**
 * A class to access EndpointList data stored as a json file on the hard disk.
 */
public class JsonEndpointListStorage implements EndpointListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEndpointListStorage.class);

    private Path filePath;

    public JsonEndpointListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEndpointListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEndpointList> readEndpointList() throws DataConversionException {
        return readEndpointList(filePath);
    }

    /**
     * Similar to {@link #readEndpointList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEndpointList> readEndpointList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEndpointList> jsonEndpointList = JsonUtil.readJsonFile(
                filePath, JsonSerializableEndpointList.class);
        if (!jsonEndpointList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEndpointList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEndpointList(ReadOnlyEndpointList endpointList) throws IOException {
        saveEndpointList(endpointList, filePath);
    }

    /**
     * Similar to {@link #saveEndpointList(ReadOnlyEndpointList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEndpointList(ReadOnlyEndpointList endpointList, Path filePath) throws IOException {
        requireNonNull(endpointList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEndpointList(endpointList), filePath);
    }

}
