package seedu.budgetbaby.abstorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.budgetbaby.abmodel.ReadOnlyAddressBook;
import seedu.budgetbaby.commons.exceptions.DataConversionException;
import seedu.budgetbaby.model.ReadOnlyUserPrefs;
import seedu.budgetbaby.model.UserPrefs;
import seedu.budgetbaby.storage.UserPrefsStorage;

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
