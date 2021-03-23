package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
/**
 * Manages storage of PropertyBook and AppointmentBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UserPrefsStorage userPrefsStorage;
    private PropertyBookStorage propertyBookStorage;
    private AppointmentBookStorage appointmentBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AppointmentBookStorage}, {@code PropertyBookStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AppointmentBookStorage appointmentBookStorage, PropertyBookStorage propertyBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.appointmentBookStorage = appointmentBookStorage;
        this.propertyBookStorage = propertyBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // =====  UserPrefs ==========================================================================================

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

    // =====  PropertyBook ====================================================================================

    @Override
    public Path getPropertyBookFilePath() {
        return propertyBookStorage.getPropertyBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPropertyBook> readPropertyBook() throws DataConversionException, IOException {
        return readPropertyBook(propertyBookStorage.getPropertyBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPropertyBook> readPropertyBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return propertyBookStorage.readPropertyBook(filePath);
    }

    @Override
    public void savePropertyBook(ReadOnlyPropertyBook propertyBook) throws IOException {
        savePropertyBook(propertyBook, propertyBookStorage.getPropertyBookFilePath());
    }

    @Override
    public void savePropertyBook(ReadOnlyPropertyBook propertyBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        propertyBookStorage.savePropertyBook(propertyBook, filePath);
    }

    // =====  AppointmentBook ====================================================================================

    @Override
    public Path getAppointmentBookFilePath() {
        return appointmentBookStorage.getAppointmentBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException {
        return readAppointmentBook(appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return appointmentBookStorage.readAppointmentBook(filePath);
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException {
        saveAppointmentBook(appointmentBook, appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        appointmentBookStorage.saveAppointmentBook(appointmentBook, filePath);
    }

}
