package seedu.booking.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.booking.commons.core.LogsCenter;
import seedu.booking.commons.exceptions.DataConversionException;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.ReadOnlyUserPrefs;
import seedu.booking.model.UserPrefs;

/**
 * Manages storage of BookingSystem data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BookingSystemStorage bookingSystemStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BookingSystemStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BookingSystemStorage bookingSystemStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.bookingSystemStorage = bookingSystemStorage;
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


    // ================ BookingSystem methods ==============================

    @Override
    public Path getBookingSystemFilePath() {
        return bookingSystemStorage.getBookingSystemFilePath();
    }

    @Override
    public Optional<ReadOnlyBookingSystem> readBookingSystem() throws DataConversionException, IOException {
        return readBookingSystem(bookingSystemStorage.getBookingSystemFilePath());
    }

    @Override
    public Optional<ReadOnlyBookingSystem> readBookingSystem(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bookingSystemStorage.readBookingSystem(filePath);
    }

    @Override
    public void saveBookingSystem(ReadOnlyBookingSystem bookingSystem) throws IOException {
        saveBookingSystem(bookingSystem, bookingSystemStorage.getBookingSystemFilePath());
    }

    @Override
    public void saveBookingSystem(ReadOnlyBookingSystem bookingSystem, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bookingSystemStorage.saveBookingSystem(bookingSystem, filePath);
    }

}
