package seedu.weeblingo.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.commons.exceptions.IllegalValueException;
import seedu.weeblingo.commons.util.FileUtil;
import seedu.weeblingo.commons.util.JsonUtil;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;

/**
 * A class to access FlashcardBook data stored as a json file on the hard disk.
 */
public class JsonFlashcardBookStorage implements FlashcardBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFlashcardBookStorage.class);

    private Path filePath;

    public JsonFlashcardBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFlashcardBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook() throws DataConversionException {
        return readFlashcardBook(filePath);
    }

    /**
     * Similar to {@link #readFlashcardBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFlashcardBook> flashcardBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableFlashcardBook.class);
        if (!flashcardBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(flashcardBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook) throws IOException {
        saveFlashcardBook(flashcardBook, filePath);
    }

    /**
     * Similar to {@link #saveFlashcardBook(ReadOnlyFlashcardBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook, Path filePath) throws IOException {
        requireNonNull(flashcardBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFlashcardBook(flashcardBook), filePath);
    }

}
