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
import seedu.address.model.ReadOnlyTutorBook;

/**
 * A class to access TutorBook data stored as a json file on the hard disk.
 */
public class JsonTutorBookStorage implements TutorBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTutorBookStorage.class);

    private Path filePath;

    public JsonTutorBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTutorBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTutorBook> readTutorBook() throws DataConversionException {
        return readTutorBook(filePath);
    }

    /**
     * Similar to {@link #readTutorBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTutorBook> readTutorBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTutorBook> jsonTutorBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTutorBook.class);
        if (!jsonTutorBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTutorBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTutorBook(ReadOnlyTutorBook tutorBook) throws IOException {
        saveTutorBook(tutorBook, filePath);
    }

    /**
     * Similar to {@link #saveTutorBook(ReadOnlyTutorBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTutorBook(ReadOnlyTutorBook tutorBook, Path filePath) throws IOException {
        requireNonNull(tutorBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTutorBook(tutorBook), filePath);
    }

}
