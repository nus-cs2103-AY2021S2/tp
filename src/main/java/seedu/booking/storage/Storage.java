package seedu.booking.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.booking.commons.exceptions.DataConversionException;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.ReadOnlyUserPrefs;
import seedu.booking.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends BookingSystemStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getBookingSystemFilePath();

    @Override
    Optional<ReadOnlyBookingSystem> readBookingSystem() throws DataConversionException, IOException;

    @Override
    void saveBookingSystem(ReadOnlyBookingSystem bookingSystem) throws IOException;

}
