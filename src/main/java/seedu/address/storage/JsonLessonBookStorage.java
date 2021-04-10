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
import seedu.address.model.ReadOnlyLessonBook;

/**
 * A class to access LessonBook data stored as a json file on the hard disk.
 */
public class JsonLessonBookStorage implements LessonBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLessonBookStorage.class);

    private Path filePath;

    public JsonLessonBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLessonBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException {
        return readLessonBook(filePath);
    }

    /**
     * Similar to {@link #readLessonBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLessonBook> readLessonBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableLessonBook> jsonLessonBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableLessonBook.class);
        if (!jsonLessonBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLessonBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException {
        saveLessonBook(lessonBook, filePath);
    }

    /**
     * Similar to {@link #saveLessonBook(ReadOnlyLessonBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLessonBook(ReadOnlyLessonBook lessonBook, Path filePath) throws IOException {
        requireNonNull(lessonBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLessonBook(lessonBook), filePath);
    }

}
