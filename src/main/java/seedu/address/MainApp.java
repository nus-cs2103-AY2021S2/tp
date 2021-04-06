package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AppointmentBook;
import seedu.address.model.BudgetBook;
import seedu.address.model.GradeBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TutorBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventTracker;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.ReminderTracker;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AppointmentBookStorage;
import seedu.address.storage.GradeBookStorage;
import seedu.address.storage.JsonAppointmentBookStorage;
import seedu.address.storage.JsonGradeBookStorage;
import seedu.address.storage.JsonReminderTrackerStorage;
import seedu.address.storage.JsonScheduleTrackerStorage;
import seedu.address.storage.JsonTutorBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.ReminderTrackerStorage;
import seedu.address.storage.ScheduleTrackerStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.TutorBookStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 1, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private static final String APPOINTMENT_BOOK_NOT_FOUND = "Data file not found. "
            + "Will be starting with a sample Appointment Book";
    private static final String TUTOR_BOOK_NOT_FOUND = "Data file not found. Will "
            + "be starting with a sample TutorBook";
    private static final String GRADE_BOOK_NOT_FOUND = "Data file not found. Will "
            + "be starting with a sample GradeBook";
    private static final String SCHEDULE_TRACKER_NOT_FOUND = "Data file not found. Will "
            + "be starting with a sample Schedule Tracker";
    private static final String REMINDER_TRACKER_NOT_FOUND = "Data file not found. Will "
            + "be starting with a sample Reminder Tracker";

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Tutor Tracker ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TutorBookStorage tutorBookStorage = new JsonTutorBookStorage(userPrefs.getTutorBookFilePath());
        AppointmentBookStorage appointmentBookStorage =
                new JsonAppointmentBookStorage(userPrefs.getAppointmentBookFilePath());
        GradeBookStorage gradeBookStorage = new JsonGradeBookStorage(userPrefs.getGradeBookFilePath());

        ScheduleTrackerStorage scheduleTrackerStorage =
                new JsonScheduleTrackerStorage(userPrefs.getScheduleTrackerFilePath());
        ReminderTrackerStorage reminderTrackerStorage =
                new JsonReminderTrackerStorage(userPrefs.getReminderTrackerFilePath());

        storage = new StorageManager(tutorBookStorage, userPrefsStorage, appointmentBookStorage,
                gradeBookStorage, scheduleTrackerStorage, reminderTrackerStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s tutor book, appointment book, grade book,
     * schedule tracker, reminder tracker and {@code userPrefs}. <br>
     * The data from the sample tutor book, appointment book, grade book, schedule tracker and reminder tracker will be
     * used instead if {@code storage}'s respective books/trackers are not found,
     * or an empty respective books/trackers will be used instead if errors occur when reading {@code storage}'s tutor
     * book, appointment book, grade book, schedule tracker and reminder tracker.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTutorBook> tutorBookOptional;
        Optional<ReadOnlyAppointmentBook> appointmentBookOptional;
        Optional<ReadOnlyGradeBook> gradeBookOptional;
        Optional<ReadOnlyScheduleTracker> scheduleTrackerOptional;
        Optional<ReadOnlyReminderTracker> reminderTrackerOptional;

        ReadOnlyTutorBook initialData;
        ReadOnlyAppointmentBook initialAppointments;
        ReadOnlyGradeBook initialGrades;
        ReadOnlyScheduleTracker initialSchedules;
        ReadOnlyReminderTracker initialReminders;

        try {
            tutorBookOptional = storage.readTutorBook();
            if (!tutorBookOptional.isPresent()) {
                logger.info(TUTOR_BOOK_NOT_FOUND);
            }
            initialData = tutorBookOptional.orElseGet(SampleDataUtil::getSampleTutorBook);
        } catch (DataConversionException e) {
            logger.warning("TutorBook: Data file not in the correct format. "
                    + "Will be starting with an empty TutorBook");
            initialData = new TutorBook();
        } catch (IOException e) {
            logger.warning("TutorBook: Problem while reading from the file. "
                    + "Will be starting with an empty TutorBook");
            initialData = new TutorBook();
        }

        try {
            appointmentBookOptional = storage.readAppointmentBook();
            if (!appointmentBookOptional.isPresent()) {
                logger.info(APPOINTMENT_BOOK_NOT_FOUND);
            }
            initialAppointments =
                    appointmentBookOptional.orElseGet(SampleDataUtil::getSampleAppointmentBook);
        } catch (DataConversionException e) {
            logger.warning("AppointmentBook: Data file not in the correct format. "
                    + "Will be starting with an empty AppointmentBook");
            initialAppointments = new AppointmentBook();
        } catch (IOException e) {
            logger.warning("AppointmentBook: Problem while reading from the file. "
                    + "Will be starting with an empty AppointmentBook");
            initialAppointments = new AppointmentBook();
        }

        BudgetBook budgetBook = storage.readBudgetBook();

        try {
            gradeBookOptional = storage.readGradeBook();
            if (!gradeBookOptional.isPresent()) {
                logger.info(GRADE_BOOK_NOT_FOUND);
            }
            initialGrades = gradeBookOptional.orElseGet(SampleDataUtil::getSampleGradeBook);
        } catch (DataConversionException e) {
            logger.warning("GradeBook: Data file not in the correct format. "
                    + "Will be starting with an empty GradeBook");
            initialGrades = new GradeBook();
        } catch (IOException e) {
            logger.warning("GradeBook: Problem while reading from the file. "
                    + "Will be starting with an empty GradeBook");
            initialGrades = new GradeBook();
        }

        try {
            scheduleTrackerOptional = storage.readScheduleTracker();
            if (!scheduleTrackerOptional.isPresent()) {
                logger.info(SCHEDULE_TRACKER_NOT_FOUND);
            }
            initialSchedules = scheduleTrackerOptional.orElseGet(SampleDataUtil::getSampleScheduleTracker);
        } catch (DataConversionException e) {
            logger.warning("ScheduleTracker: Data file not in the correct format. "
                    + "Will be starting with an empty ScheduleTracker");
            initialSchedules = new ScheduleTracker();
        } catch (IOException e) {
            logger.warning("ScheduleTracker: Problem while reading from the file. "
                    + "Will be starting with an empty ScheduleTracker");
            initialSchedules = new ScheduleTracker();
        }

        try {
            reminderTrackerOptional = storage.readReminderTracker();
            if (!reminderTrackerOptional.isPresent()) {
                logger.info(REMINDER_TRACKER_NOT_FOUND);
            }
            initialReminders = reminderTrackerOptional.orElseGet(SampleDataUtil::getSampleReminderTracker);
        } catch (DataConversionException e) {
            logger.warning("ReminderTracker: Data file not in the correct format. "
                    + "Will be starting with an empty ReminderTracker");
            initialReminders = new ReminderTracker();
        } catch (IOException e) {
            logger.warning("ReminderTracker: Problem while reading from the file. "
                    + "Will be starting with an empty ReminderTracker");
            initialReminders = new ReminderTracker();
        }

        EventTracker eventTracker = new EventTracker(initialAppointments, initialSchedules);
        if (eventTracker.hasClashingDateTime()) {
            logger.warning("AppointmentBook: Conflicting TIME_FROM and TIME_TO detected. "
                    + "Will be starting with an empty AppointmentBook");
            logger.warning("ScheduleTracker: Conflicting TIME_FROM and TIME_TO detected. "
                    + "Will be starting with an empty ScheduleTracker");
            initialAppointments = new AppointmentBook();
            initialSchedules = new ScheduleTracker();
        }
        return new ModelManager(initialData, userPrefs, initialAppointments,
                budgetBook, initialGrades, initialSchedules, initialReminders);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TutorBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Tutor Tracker " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Tutor Tracker ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
