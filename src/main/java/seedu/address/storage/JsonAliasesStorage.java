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
import seedu.address.model.ReadOnlyUniqueAliasMap;

/**
 * A class to access Aliases data stored as a json file on the hard disk.
 */
public class JsonAliasesStorage implements AliasesStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAliasesStorage.class);

    private Path filePath;

    public JsonAliasesStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAliasesFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniqueAliasMap> readAliases() throws DataConversionException {
        return readAliases(filePath);
    }

    /**
     * Similar to {@link #readAliases()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUniqueAliasMap> readAliases(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAliases> jsonAliases = JsonUtil.readJsonFile(
                filePath, JsonSerializableAliases.class);
        if (jsonAliases.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAliases.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAliases(ReadOnlyUniqueAliasMap aliases) throws IOException {
        saveAliases(aliases, filePath);
    }

    /**
     * Similar to {@link #saveAliases(ReadOnlyUniqueAliasMap)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAliases(ReadOnlyUniqueAliasMap aliases, Path filePath) throws IOException {
        requireNonNull(aliases);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAliases(aliases), filePath);
    }

}
