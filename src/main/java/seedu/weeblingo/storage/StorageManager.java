package seedu.weeblingo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.ReadOnlyUserPrefs;
import seedu.weeblingo.model.UserPrefs;

/**
 * Manages storage of FlashcardBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlashcardBookStorage flashcardBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashcardBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashcardBookStorage flashcardBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.flashcardBookStorage = flashcardBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FlashcardBook methods ==============================

    @Override
    public Path getFlashcardBookFilePath() {
        return flashcardBookStorage.getFlashcardBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook() throws DataConversionException, IOException {
        return readFlashcardBook(flashcardBookStorage.getFlashcardBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFlashcardBook> readFlashcardBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashcardBookStorage.readFlashcardBook(filePath);
    }

    @Override
    public void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook) throws IOException {
        saveFlashcardBook(flashcardBook, flashcardBookStorage.getFlashcardBookFilePath());
    }

    @Override
    public void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashcardBookStorage.saveFlashcardBook(flashcardBook, filePath);
    }

}
