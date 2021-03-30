package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAppointmentBook;

/**
 * Represents a storage for {@link seedu.address.model.AppointmentBook}
 */
public interface AppointmentBookStorage {

    /**
     * @return File path of Appointment Book data file
     */
    Path getAppointmentBookFilePath();

    /**
     * Returns Appointment book as a {@link ReadOnlyAppointmentBook}
     * @return {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException,
            IOException;

    /**
     * @param filePath File path of Appointment book
     * @return {@code Optional.empty()} if filePath is an empty Appointment Book
     * @throws DataConversionException
     * @throws IOException
     */
    Optional<ReadOnlyAppointmentBook> readAppointmentBook(Path filePath) throws DataConversionException,
            IOException;


    /**
     * Saves the given {@link ReadOnlyAppointmentBook} to the storage.
     * @param appointmentBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException;

    /**
     * @see #saveAppointmentBook(ReadOnlyAppointmentBook)
     */
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException;


}
