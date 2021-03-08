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
import seedu.address.model.ReadOnlySochedule;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonSocheduleStorage implements SocheduleStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSocheduleStorage.class);

    private Path filePath;

    public JsonSocheduleStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSocheduleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySochedule> readSochedule() throws DataConversionException {
        return readSochedule(filePath);
    }

    /**
     * Similar to {@link #readSochedule()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySochedule> readSochedule(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSochedule> jsonSochedule = JsonUtil.readJsonFile(
                filePath, JsonSerializableSochedule.class);
        if (!jsonSochedule.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSochedule.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSochedule(ReadOnlySochedule sochedule) throws IOException {
        saveSochedule(sochedule, filePath);
    }

    /**
     * Similar to {@link #saveSochedule(ReadOnlySochedule)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSochedule(ReadOnlySochedule sochedule, Path filePath) throws IOException {
        requireNonNull(sochedule);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSochedule(sochedule), filePath);
    }

}
