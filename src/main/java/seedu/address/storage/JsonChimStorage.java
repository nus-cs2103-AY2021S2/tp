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
import seedu.address.model.ReadOnlyChim;

/**
 * A class to access CHIM data stored as a json file on the hard disk.
 */
public class JsonChimStorage implements ChimStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonChimStorage.class);

    private Path filePath;

    public JsonChimStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getChimFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyChim> readChim() throws DataConversionException {
        return readChim(filePath);
    }

    /**
     * Similar to {@link #readChim()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyChim> readChim(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableChim> jsonChim = JsonUtil.readJsonFile(
                filePath, JsonSerializableChim.class);
        if (!jsonChim.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonChim.get().toModelType());
        } catch (IllegalValueException | IllegalArgumentException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveChim(ReadOnlyChim chim) throws IOException {
        saveChim(chim, filePath);
    }

    /**
     * Similar to {@link #saveChim(ReadOnlyChim)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveChim(ReadOnlyChim chim, Path filePath) throws IOException {
        requireNonNull(chim);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableChim(chim), filePath);
    }

}
