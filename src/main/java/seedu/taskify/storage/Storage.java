package seedu.taskify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taskify.commons.exceptions.DataConversionException;
import seedu.taskify.model.ReadOnlyAddressBook;
import seedu.taskify.model.ReadOnlyUserPrefs;
import seedu.taskify.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

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

}
