package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BudgetBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private AppointmentBookStorage appointmentBookStorage;
    private BudgetBookStorage budgetBookStorage;
    private GradeBookStorage gradeBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          AppointmentBookStorage appointmentBookStorage,
                          GradeBookStorage gradeBookStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.appointmentBookStorage = appointmentBookStorage;
        //TODO improve handling of budget book
        this.budgetBookStorage = new BudgetBookStorage();
        this.gradeBookStorage = gradeBookStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ AppointmentBook methods ==========================

    @Override
    public Path getAppointmentBookFilePath() {
        return appointmentBookStorage.getAppointmentBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook()
            throws DataConversionException, IOException {
        return readAppointmentBook(appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return appointmentBookStorage.readAppointmentBook(filePath);
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook addressBook) throws IOException {
        saveAppointmentBook(addressBook, appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        appointmentBookStorage.saveAppointmentBook(appointmentBook, filePath);
    }


    // ================ BudgetBook methods ==========================
    @Override
    public BudgetBook readBudgetBook() {
        return this.budgetBookStorage.loadBudgetBook();
    }

    @Override
    public void saveBudgetBook(BudgetBook budgetBook) throws IOException {
        budgetBookStorage.saveBudget(budgetBook.getBudget().getValue(),
                budgetBook.getBudget().getTotalCost());
    }

    // ================ GradeBook methods ==========================

    @Override
    public Path getGradeBookFilePath() {
        return gradeBookStorage.getGradeBookFilePath();
    }

    @Override
    public Optional<ReadOnlyGradeBook> readGradeBook()
            throws DataConversionException, IOException {
        return readGradeBook(gradeBookStorage.getGradeBookFilePath());
    }

    @Override
    public Optional<ReadOnlyGradeBook> readGradeBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return gradeBookStorage.readGradeBook(filePath);
    }

    @Override
    public void saveGradeBook(ReadOnlyGradeBook gradeBook) throws IOException {
        saveGradeBook(gradeBook, gradeBookStorage.getGradeBookFilePath());
    }

    @Override
    public void saveGradeBook(ReadOnlyGradeBook gradeBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        gradeBookStorage.saveGradeBook(gradeBook, filePath);
    }

}
