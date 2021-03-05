package seedu.storemando.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.commons.exceptions.IllegalValueException;
import seedu.storemando.commons.util.FileUtil;
import seedu.storemando.commons.util.JsonUtil;
import seedu.storemando.model.ReadOnlyStoreMando;

/**
 * A class to access StoreMando data stored as a json file on the hard disk.
 */
public class JsonStoreMandoStorage implements StoreMandoStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStoreMandoStorage.class);

    private final Path filePath;

    public JsonStoreMandoStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStoreMandoFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStoreMando> readStoreMando() throws DataConversionException {
        return readStoreMando(filePath);
    }

    /**
     * Similar to {@link #readStoreMando()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStoreMando> readStoreMando(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStoreMando> jsonStoreMando = JsonUtil.readJsonFile(
            filePath, JsonSerializableStoreMando.class);
        if (!jsonStoreMando.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStoreMando.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStoreMando(ReadOnlyStoreMando storeMando) throws IOException {
        saveStoreMando(storeMando, filePath);
    }

    /**
     * Similar to {@link #saveStoreMando(ReadOnlyStoreMando)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStoreMando(ReadOnlyStoreMando storeMando, Path filePath) throws IOException {
        requireNonNull(storeMando);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStoreMando(storeMando), filePath);
    }

}
