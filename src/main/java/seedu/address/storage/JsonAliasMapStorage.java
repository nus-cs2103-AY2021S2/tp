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
 * A class to access AliasMap data stored as a json file on the hard disk.
 */
public class JsonAliasMapStorage implements AliasMapStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAliasMapStorage.class);

    private Path filePath;

    public JsonAliasMapStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAliasMapFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniqueAliasMap> readAliasMap() throws DataConversionException {
        return readAliasMap(filePath);
    }

    /**
     * Similar to {@link #readAliasMap()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUniqueAliasMap> readAliasMap(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAliasMap> jsonAliasMap = JsonUtil.readJsonFile(
                filePath, JsonSerializableAliasMap.class);
        if (jsonAliasMap.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAliasMap.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAliasMap(ReadOnlyUniqueAliasMap aliasMap) throws IOException {
        saveAliasMap(aliasMap, filePath);
    }

    /**
     * Similar to {@link #saveAliasMap(ReadOnlyUniqueAliasMap)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAliasMap(ReadOnlyUniqueAliasMap aliasMap, Path filePath) throws IOException {
        requireNonNull(aliasMap);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAliasMap(aliasMap), filePath);
    }

}
