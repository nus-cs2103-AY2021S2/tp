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
import seedu.address.model.ReadOnlyTeachingAssistant;

/**
 * A class to access TeachingAssistant data stored as a json file on the hard disk.
 */
public class JsonTeachingAssistantStorage implements TeachingAssistantStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTeachingAssistantStorage.class);

    private Path filePath;

    public JsonTeachingAssistantStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTeachingAssistantFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTeachingAssistant> readTeachingAssistant() throws DataConversionException {
        return readTeachingAssistant(filePath);
    }

    /**
     * Similar to {@link #readTeachingAssistant()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTeachingAssistant> readTeachingAssistant(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTeachingAssistant> jsonTeachingAssistant = JsonUtil.readJsonFile(
                filePath, JsonSerializableTeachingAssistant.class);
        if (!jsonTeachingAssistant.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTeachingAssistant.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant) throws IOException {
        saveTeachingAssistant(teachingAssistant, filePath);
    }

    /**
     * Similar to {@link #saveTeachingAssistant(ReadOnlyTeachingAssistant)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTeachingAssistant(ReadOnlyTeachingAssistant teachingAssistant, Path filePath) throws IOException {
        requireNonNull(teachingAssistant);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTeachingAssistant(teachingAssistant), filePath);
    }

}
