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
import seedu.address.model.ReadOnlyWardrobe;

/**
 * A class to access Wardrobe data stored as a json file on the hard disk.
 */
public class JsonWardrobeStorage implements WardrobeStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWardrobeStorage.class);

    private Path filePath;

    public JsonWardrobeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWardrobeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWardrobe> readWardrobe() throws DataConversionException {
        return readWardrobe(filePath);
    }

    /**
     * Similar to {@link #readWardrobe()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyWardrobe> readWardrobe(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableWardrobe> jsonWardrobe = JsonUtil.readJsonFile(
                filePath, JsonSerializableWardrobe.class);
        if (!jsonWardrobe.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWardrobe.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveWardrobe(ReadOnlyWardrobe wardrobe) throws IOException {
        saveWardrobe(wardrobe, filePath);
    }

    /**
     * Similar to {@link #saveWardrobe(ReadOnlyWardrobe)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWardrobe(ReadOnlyWardrobe wardrobe, Path filePath) throws IOException {
        requireNonNull(wardrobe);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWardrobe(wardrobe), filePath);
    }

}
