package seedu.partyplanet.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.partyplanet.commons.exceptions.DataConversionException;
import seedu.partyplanet.model.ReadOnlyAddressBook;
import seedu.partyplanet.model.ReadOnlyEventBook;
import seedu.partyplanet.model.ReadOnlyUserPrefs;
import seedu.partyplanet.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, EventBookStorage, UserPrefsStorage {

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
    Path getEventBookFilePath();

    @Override
    Optional<ReadOnlyEventBook> readEventBook() throws DataConversionException, IOException;

    @Override
    void saveEventBook(ReadOnlyEventBook eventBook) throws IOException;

}
