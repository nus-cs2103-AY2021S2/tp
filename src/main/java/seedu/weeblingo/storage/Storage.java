package seedu.weeblingo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.ReadOnlyUserPrefs;
import seedu.weeblingo.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FlashcardBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFlashcardBookFilePath();

    @Override
    Optional<ReadOnlyFlashcardBook> readFlashcardBook() throws DataConversionException, IOException;

    @Override
    void saveFlashcardBook(ReadOnlyFlashcardBook flashcardBook) throws IOException;

}
