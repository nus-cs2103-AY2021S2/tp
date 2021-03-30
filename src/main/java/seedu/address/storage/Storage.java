package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PropertyBookStorage, AppointmentBookStorage, UserPrefsStorage {

    @Override
    Path getUserPrefsFilePath();

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPropertyBookFilePath();

    @Override
    Optional<ReadOnlyPropertyBook> readPropertyBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyPropertyBook> readPropertyBook(Path filePath) throws DataConversionException, IOException;

    @Override
    void savePropertyBook(ReadOnlyPropertyBook propertyBook) throws IOException;

    @Override
    void savePropertyBook(ReadOnlyPropertyBook propertyBook, Path filePath) throws IOException;

    @Override
    Path getAppointmentBookFilePath();

    @Override
    Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAppointmentBook> readAppointmentBook(Path filePath) throws DataConversionException, IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException;
}
