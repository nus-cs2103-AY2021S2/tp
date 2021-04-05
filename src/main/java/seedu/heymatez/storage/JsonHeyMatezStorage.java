package seedu.heymatez.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.heymatez.commons.core.LogsCenter;
import seedu.heymatez.commons.exceptions.DataConversionException;
import seedu.heymatez.commons.exceptions.IllegalValueException;
import seedu.heymatez.commons.util.FileUtil;
import seedu.heymatez.commons.util.JsonUtil;
import seedu.heymatez.model.ReadOnlyHeyMatez;

/**
 * A class to access HeyMatez data stored as a json file on the hard disk.
 */
public class JsonHeyMatezStorage implements HeyMatezStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHeyMatezStorage.class);

    private Path filePath;

    public JsonHeyMatezStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHeyMatezFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHeyMatez> readHeyMatez() throws DataConversionException {
        return readHeyMatez(filePath);
    }

    /**
     * Similar to {@link #readHeyMatez()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHeyMatez> readHeyMatez(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHeyMatez> jsonHeyMatez = JsonUtil.readJsonFile(
                filePath, JsonSerializableHeyMatez.class);
        if (!jsonHeyMatez.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHeyMatez.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHeyMatez(ReadOnlyHeyMatez heyMatez) throws IOException {
        saveHeyMatez(heyMatez, filePath);
    }

    /**
     * Similar to {@link #saveHeyMatez(ReadOnlyHeyMatez)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHeyMatez(ReadOnlyHeyMatez heyMatez, Path filePath) throws IOException {
        requireNonNull(heyMatez);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHeyMatez(heyMatez), filePath);
    }

}
