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
import seedu.address.model.ReadOnlyFlashBack;

/**
 * A class to access FlashBack data stored as a json file on the hard disk.
 */
public class JsonFlashBackStorage implements FlashBackStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFlashBackStorage.class);

    private Path filePath;

    public JsonFlashBackStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFlashBackFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFlashBack> readFlashBack() throws DataConversionException {
        return readFlashBack(filePath);
    }

    /**
     * Similar to {@link #readFlashBack()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFlashBack> readFlashBack(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFlashBack> jsonFlashBack = JsonUtil.readJsonFile(
                filePath, JsonSerializableFlashBack.class);
        if (!jsonFlashBack.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFlashBack.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFlashBack(ReadOnlyFlashBack flashBack) throws IOException {
        saveFlashBack(flashBack, filePath);
    }

    /**
     * Similar to {@link #saveFlashBack(ReadOnlyFlashBack)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFlashBack(ReadOnlyFlashBack flashBack, Path filePath) throws IOException {
        requireNonNull(flashBack);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFlashBack(flashBack), filePath);
    }

}
