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
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.exceptions.AppointmentDoctorNotInDoctorRecordsException;
import seedu.address.model.appointment.exceptions.AppointmentPatientNotInPatientRecordsException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.AppointmentScheduleStorage;
import seedu.address.storage.JsonAppointmentScheduleStorage;
import seedu.address.storage.JsonDoctorRecordsStorage;
import seedu.address.storage.JsonPatientRecordsStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, false);

    private static final Logger LOGGER = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        LOGGER.info("=============================[ Initializing App-Ointment ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage<Patient> patientRecordsStorage = new JsonPatientRecordsStorage(
                userPrefs.getPatientRecordsFilePath());
        AddressBookStorage<Doctor> doctorRecordsStorage = new JsonDoctorRecordsStorage(
                userPrefs.getDoctorRecordsFilePath());
        AppointmentScheduleStorage appointmentScheduleStorage = new JsonAppointmentScheduleStorage(
                userPrefs.getAppointmentScheduleFilePath());

        storage = new StorageManager(patientRecordsStorage, doctorRecordsStorage, appointmentScheduleStorage,
                userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);
        logic = new LogicManager(model, storage);
        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s appointment schedule,
     * patient records, doctor records and {@code userPrefs}.
     * The data from the sample patient records will be used instead if {@code storage}'s patient records are not
     * found, or an empty patient address book will be used instead if errors occur when reading
     * {@code storage}'s patient records. The same applies for doctor records, and appointment schedule.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        // passing storage as a parameter ensures that storage is initialised
        // separately initialise them to avoid the case where compiler initialises
        // appointment schedule before patient/doctor records
        ReadOnlyAddressBook<Patient> patientRecords = initPatientRecords(storage);
        ReadOnlyAddressBook<Doctor> doctorRecords = initDoctorRecords(storage);
        ReadOnlyAppointmentSchedule appointmentSchedule =
                initAppointmentSchedule(storage, patientRecords, doctorRecords);
        return new ModelManager(patientRecords, doctorRecords, appointmentSchedule, userPrefs);
    }

    private ReadOnlyAddressBook<Patient> initPatientRecords(Storage storage) {
        Optional<ReadOnlyAddressBook<Patient>> patientRecordsOptional;
        ReadOnlyAddressBook<Patient> patientRecords;

        try {
            patientRecordsOptional = storage.readPatientRecords();
            if (patientRecordsOptional.isEmpty()) {
                LOGGER.info("Patient data file not found. Will be starting with a sample Patient Record");
            }

            patientRecords = patientRecordsOptional.orElseGet(SampleDataUtil::getSamplePatientRecords);
        } catch (DataConversionException e) {
            LOGGER.warning("Patient data file not in the correct format. Will be starting with an empty"
                    + " Patient Record");
            patientRecords = new AddressBook<>();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the patient data file. Will be starting with an empty"
                    + " Patient Record");
            patientRecords = new AddressBook<>();
        }

        return patientRecords;
    }

    private ReadOnlyAddressBook<Doctor> initDoctorRecords(Storage storage) {
        Optional<ReadOnlyAddressBook<Doctor>> doctorRecordsOptional;
        ReadOnlyAddressBook<Doctor> doctorRecords;

        try {
            doctorRecordsOptional = storage.readDoctorRecords();
            if (doctorRecordsOptional.isEmpty()) {
                LOGGER.info("Doctor data file not found. Will be starting with a sample Doctor Record");
            }

            doctorRecords = doctorRecordsOptional.orElseGet(SampleDataUtil::getSampleDoctorRecords);
        } catch (DataConversionException e) {
            LOGGER.warning("Doctor data file not in the correct format. Will be starting with an empty"
                    + " Doctor Record");
            doctorRecords = new AddressBook<>();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the doctor data file. Will be starting with an empty"
                    + " Doctor Record");
            doctorRecords = new AddressBook<>();
        }

        return doctorRecords;
    }

    private ReadOnlyAppointmentSchedule initAppointmentSchedule (Storage storage,
            ReadOnlyAddressBook<Patient> patientRecords, ReadOnlyAddressBook<Doctor> doctorRecords) {
        Optional<ReadOnlyAppointmentSchedule> appointmentScheduleOptional;
        ReadOnlyAppointmentSchedule appointmentSchedule;

        // On exception or non existent data file, initialize with empty Appointment Schedule
        try {
            appointmentScheduleOptional = storage.readAppointmentSchedule();
            if (appointmentScheduleOptional.isEmpty()) {
                LOGGER.info("Appointment data file not found. Will be starting with a sample Appointment Schedule");
            }

            appointmentSchedule = appointmentScheduleOptional.orElseGet(SampleDataUtil::getSampleAppointmentSchedule);

            appointmentSchedule.checkAppointmentScheduleValidity(patientRecords, doctorRecords);
        } catch (DataConversionException e) {
            LOGGER.warning("Appointment data file not in the correct format. Will be starting with an empty"
                    + " Appointment Schedule");
            appointmentSchedule = new AppointmentSchedule();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the appointment data file. Will be starting with an empty"
                    + " Appointment Schedule");
            appointmentSchedule = new AppointmentSchedule();
        } catch (AppointmentPatientNotInPatientRecordsException e) {
            LOGGER.warning("Some Patient data in Appointment data file does not correspond to a Patient "
                    + "in the Patient data file. Will be starting with an empty Appointment Schedule");
            appointmentSchedule = new AppointmentSchedule();
        } catch (AppointmentDoctorNotInDoctorRecordsException e) {
            LOGGER.warning("Some Doctor data in Appointment data file does not correspond to a Doctor "
                    + "in the Doctor data file. Will be starting with an empty Appointment Schedule");
            appointmentSchedule = new AppointmentSchedule();
        }

        return appointmentSchedule;
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
            LOGGER.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        LOGGER.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            LOGGER.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            LOGGER.warning("Failed to save config file : " + StringUtil.getDetails(e));
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
        LOGGER.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            LOGGER.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            LOGGER.warning("Problem while reading from the file. Will be starting with an empty UserPrefs file.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            LOGGER.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Starting App-Ointment " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        LOGGER.info("============================ [ Stopping App-Ointment ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            LOGGER.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
