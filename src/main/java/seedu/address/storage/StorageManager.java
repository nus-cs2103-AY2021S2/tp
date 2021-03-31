package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BudgetBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;

/**
 * Manages storage of TutorBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TutorBookStorage tutorBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private AppointmentBookStorage appointmentBookStorage;
    private BudgetBookStorage budgetBookStorage;
    private GradeBookStorage gradeBookStorage;
    private ScheduleTrackerStorage scheduleTrackerStorage;
    private ReminderTrackerStorage reminderTrackerStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TutorBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TutorBookStorage tutorBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          AppointmentBookStorage appointmentBookStorage,
                          GradeBookStorage gradeBookStorage,
                          ScheduleTrackerStorage scheduleTrackerStorage,
                          ReminderTrackerStorage reminderTrackerStorage) {
        super();
        this.tutorBookStorage = tutorBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.appointmentBookStorage = appointmentBookStorage;
        //TODO improve handling of budget book
        this.budgetBookStorage = new BudgetBookStorage();
        this.gradeBookStorage = gradeBookStorage;
        this.scheduleTrackerStorage = scheduleTrackerStorage;
        this.reminderTrackerStorage = reminderTrackerStorage;
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


    // ================ TutorBook methods ==============================

    @Override
    public Path getTutorBookFilePath() {
        return tutorBookStorage.getTutorBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTutorBook> readTutorBook() throws DataConversionException, IOException {
        return readTutorBook(tutorBookStorage.getTutorBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTutorBook> readTutorBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tutorBookStorage.readTutorBook(filePath);
    }

    @Override
    public void saveTutorBook(ReadOnlyTutorBook tutorBook) throws IOException {
        saveTutorBook(tutorBook, tutorBookStorage.getTutorBookFilePath());
    }

    @Override
    public void saveTutorBook(ReadOnlyTutorBook tutorBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tutorBookStorage.saveTutorBook(tutorBook, filePath);
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
    public void saveAppointmentBook(ReadOnlyAppointmentBook tutorBook) throws IOException {
        saveAppointmentBook(tutorBook, appointmentBookStorage.getAppointmentBookFilePath());
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
        if (budgetBook.hasBudget()) {
            budgetBookStorage.saveBudget(budgetBook.getBudget().getValue(),
                    budgetBook.getBudget().getTotalCost());
        } else {
            budgetBookStorage.saveBudget();
        }

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

    // ================ ScheduleTracker methods ==========================

    @Override
    public Path getScheduleTrackerFilePath() {
        return scheduleTrackerStorage.getScheduleTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyScheduleTracker> readScheduleTracker() throws DataConversionException, IOException {
        return readScheduleTracker(scheduleTrackerStorage.getScheduleTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyScheduleTracker> readScheduleTracker(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleTrackerStorage.readScheduleTracker(filePath);
    }

    @Override
    public void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker) throws IOException {
        saveScheduleTracker(scheduleTracker, scheduleTrackerStorage.getScheduleTrackerFilePath());
    }

    @Override
    public void saveScheduleTracker(ReadOnlyScheduleTracker scheduleTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleTrackerStorage.saveScheduleTracker(scheduleTracker, filePath);
    }

    @Override
    public Path getReminderTrackerFilePath() {
        return reminderTrackerStorage.getReminderTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyReminderTracker> readReminderTracker() throws DataConversionException, IOException {
        return readReminderTracker(reminderTrackerStorage.getReminderTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyReminderTracker> readReminderTracker(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return reminderTrackerStorage.readReminderTracker(filePath);
    }

    @Override
    public void saveReminderTracker(ReadOnlyReminderTracker reminderTracker) throws IOException {
        saveReminderTracker(reminderTracker, reminderTrackerStorage.getReminderTrackerFilePath());
    }

    @Override
    public void saveReminderTracker(ReadOnlyReminderTracker reminderTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        reminderTrackerStorage.saveReminderTracker(reminderTracker, filePath);
    }
}
