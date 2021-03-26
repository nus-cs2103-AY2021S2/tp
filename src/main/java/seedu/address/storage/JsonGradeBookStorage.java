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
import seedu.address.model.ReadOnlyGradeBook;

public class JsonGradeBookStorage implements GradeBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonGradeBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGradeBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException {
        return readGradeBook(filePath);
    }

    /**
     * Similar to {@link #readGradeBook(Path)} ()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGradeBook> readGradeBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGradeBook> jsonSerializableGradeBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableGradeBook.class);
        if (!jsonSerializableGradeBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSerializableGradeBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGradeBook(ReadOnlyGradeBook gradeBook) throws IOException {
        saveGradeBook(gradeBook, filePath);
    }


    /**
     * Similar to {@link #saveGradeBook(ReadOnlyGradeBook, Path)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGradeBook(ReadOnlyGradeBook gradeBook, Path filePath) throws IOException {
        requireNonNull(gradeBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGradeBook(gradeBook), filePath);
    }
}
