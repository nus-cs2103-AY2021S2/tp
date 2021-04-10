package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDatesBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, DatesBookStorage, LessonBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getDatesBookFilePath();

    @Override
    Optional<ReadOnlyDatesBook> readDatesBook() throws DataConversionException, IOException;

    @Override
    void saveDatesBook(ReadOnlyDatesBook datesBook) throws IOException;

    @Override
    Path getLessonBookFilePath();

    @Override
    Optional<ReadOnlyLessonBook> readLessonBook() throws DataConversionException, IOException;

    @Override
    void saveLessonBook(ReadOnlyLessonBook lessonBook) throws IOException;


}
