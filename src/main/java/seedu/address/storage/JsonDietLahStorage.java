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
import seedu.address.model.ReadOnlyDietLah;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonDietLahStorage implements DietLahStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDietLahStorage.class);

    private Path filePath;

    public JsonDietLahStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDietLahFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDietLah> readDietLah() throws DataConversionException {
        return readDietLah(filePath);
    }

    /**
     * Similar to {@link #readDietLah()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDietLah> readDietLah(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDietLah> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDietLah.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDietLah(ReadOnlyDietLah dietLah) throws IOException {
        saveDietLah(dietLah, filePath);
    }

    /**
     * Similar to {@link #saveDietLah(ReadOnlyDietLah)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDietLah(ReadOnlyDietLah dietLah, Path filePath) throws IOException {
        requireNonNull(dietLah);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDietLah(dietLah), filePath);
    }

}
