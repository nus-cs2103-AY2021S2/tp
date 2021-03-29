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
import seedu.address.model.ReadOnlyDatesBook;

/**
 * A class to access DatesBook data stored as a json file on the hard disk.
 */
public class JsonDatesBookStorage implements DatesBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDatesBookStorage.class);

    private Path filePath;

    public JsonDatesBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDatesBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDatesBook> readDatesBook() throws DataConversionException {
        return readDatesBook(filePath);
    }

    /**
     * Similar to {@link #readDatesBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDatesBook> readDatesBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDatesBook> jsonDatesBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDatesBook.class);
        if (!jsonDatesBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDatesBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDatesBook(ReadOnlyDatesBook datesBook) throws IOException {
        saveDatesBook(datesBook, filePath);
    }

    /**
     * Similar to {@link #saveDatesBook(ReadOnlyDatesBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDatesBook(ReadOnlyDatesBook datesBook, Path filePath) throws IOException {
        requireNonNull(datesBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDatesBook(datesBook), filePath);
    }
}
