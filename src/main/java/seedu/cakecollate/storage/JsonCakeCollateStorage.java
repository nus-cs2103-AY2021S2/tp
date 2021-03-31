package seedu.cakecollate.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.commons.util.FileUtil;
import seedu.cakecollate.commons.util.JsonUtil;
import seedu.cakecollate.model.ReadOnlyCakeCollate;

/**
 * A class to access CakeCollate data stored as a json file on the hard disk.
 */
public class JsonCakeCollateStorage implements CakeCollateStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCakeCollateStorage.class);

    private Path filePath;

    public JsonCakeCollateStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCakeCollateFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCakeCollate> readCakeCollate() throws DataConversionException {
        return readCakeCollate(filePath);
    }

    /**
     * Similar to {@link #readCakeCollate()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCakeCollate> readCakeCollate(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCakeCollate> jsonCakeCollate = JsonUtil.readJsonFile(
                filePath, JsonSerializableCakeCollate.class);
        if (!jsonCakeCollate.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCakeCollate.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCakeCollate(ReadOnlyCakeCollate cakeCollate) throws IOException {
        saveCakeCollate(cakeCollate, filePath);
    }

    /**
     * Similar to {@link #saveCakeCollate(ReadOnlyCakeCollate)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCakeCollate(ReadOnlyCakeCollate cakeCollate, Path filePath) throws IOException {
        requireNonNull(cakeCollate);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCakeCollate(cakeCollate), filePath);
    }

}
