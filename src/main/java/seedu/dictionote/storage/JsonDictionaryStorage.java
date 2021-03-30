package seedu.dictionote.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dictionote.commons.core.LogsCenter;
import seedu.dictionote.commons.exceptions.DataConversionException;
import seedu.dictionote.commons.exceptions.IllegalValueException;
import seedu.dictionote.commons.util.FileUtil;
import seedu.dictionote.commons.util.JsonUtil;
import seedu.dictionote.model.ReadOnlyDictionary;

/**
 * A class to access NoteBook data stored as a json file on the hard disk.
 */
public class JsonDictionaryStorage implements DictionaryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDictionaryStorage.class);

    private Path filePath;

    public JsonDictionaryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDictionaryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDictionary> readDictionary() throws DataConversionException {
        return readDictionary(filePath);
    }

    /**
     * Similar to {@link #readDictionary()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDictionary> readDictionary(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDictionary> jsonDictionary = JsonUtil.readJsonFile(
                filePath, JsonSerializableDictionary.class);
        if (!jsonDictionary.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDictionary.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDictionary(ReadOnlyDictionary dictionary) throws IOException {
        saveDictionary(dictionary, filePath);
    }

    /**
     * Similar to {@link #saveDictionary(ReadOnlyDictionary)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDictionary(ReadOnlyDictionary dictionary, Path filePath) throws IOException {
        requireNonNull(dictionary);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDictionary(dictionary), filePath);
    }

}
