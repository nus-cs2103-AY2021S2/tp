package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.util.Pair;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends BookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<Pair<ReadOnlyAddressBook, AppointmentBook>> readBooks() throws DataConversionException, IOException;

    @Override
    void saveBooks(ReadOnlyAddressBook addressBook, AppointmentBook appointmentBook) throws IOException;
}
