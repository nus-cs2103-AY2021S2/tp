package seedu.booking.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.booking.commons.exceptions.DataConversionException;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.ReadOnlyBookingSystem;

/**
 * Represents a storage for {@link BookingSystem}.
 */
public interface BookingSystemStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBookingSystemFilePath();

    /**
     * Returns BookingSystem data as a {@link ReadOnlyBookingSystem}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBookingSystem> readBookingSystem() throws DataConversionException, IOException;

    /**
     * @see #getBookingSystemFilePath()
     */
    Optional<ReadOnlyBookingSystem> readBookingSystem(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBookingSystem} to the storage.
     * @param bookingSystem cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBookingSystem(ReadOnlyBookingSystem bookingSystem) throws IOException;

    /**
     * @see #saveBookingSystem(ReadOnlyBookingSystem)
     */
    void saveBookingSystem(ReadOnlyBookingSystem bookingSystem, Path filePath) throws IOException;

}
