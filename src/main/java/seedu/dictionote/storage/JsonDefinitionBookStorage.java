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
import seedu.dictionote.model.ReadOnlyDefinitionBook;

/**
 * A class to access DefinitionBook data stored as a json file on the hard disk.
 */
public class JsonDefinitionBookStorage implements DefinitionBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDefinitionBookStorage.class);

    private Path filePath;

    public JsonDefinitionBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDefinitionBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDefinitionBook> readDefinitionBook() throws DataConversionException {
        return readDefinitionBook(filePath);
    }

    /**
     * Similar to {@link #readDefinitionBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDefinitionBook> readDefinitionBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDefinitionBook> jsonDefinitionBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDefinitionBook.class);
        if (!jsonDefinitionBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDefinitionBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDefinitionBook(ReadOnlyDefinitionBook definitionBook) throws IOException {
        saveDefinitionBook(definitionBook, filePath);
    }

    /**
     * Similar to {@link #saveDefinitionBook(ReadOnlyDefinitionBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDefinitionBook(ReadOnlyDefinitionBook definitionBook, Path filePath) throws IOException {
        requireNonNull(definitionBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDefinitionBook(definitionBook), filePath);
    }

}
