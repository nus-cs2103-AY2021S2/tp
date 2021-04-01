package seedu.iscam;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.iscam.commons.core.Config;
import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.commons.core.Version;
import seedu.iscam.commons.exceptions.DataConversionException;
import seedu.iscam.commons.util.ConfigUtil;
import seedu.iscam.commons.util.StringUtil;
import seedu.iscam.logic.Logic;
import seedu.iscam.logic.LogicManager;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.ReadOnlyUserPrefs;
import seedu.iscam.model.UserPrefs;
import seedu.iscam.model.util.SampleDataUtil;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.storage.ClientBookStorage;
import seedu.iscam.storage.JsonClientBookStorage;
import seedu.iscam.storage.JsonMeetingBookStorage;
import seedu.iscam.storage.JsonUserPrefsStorage;
import seedu.iscam.storage.MeetingBookStorage;
import seedu.iscam.storage.Storage;
import seedu.iscam.storage.StorageManager;
import seedu.iscam.storage.UserPrefsStorage;
import seedu.iscam.ui.Ui;
import seedu.iscam.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=======================[ Initializing ClientBook and MeetingBook ]=======================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ClientBookStorage clientBookStorage = new JsonClientBookStorage(userPrefs.getClientBookFilePath());
        MeetingBookStorage meetingBookStorage = new JsonMeetingBookStorage(userPrefs.getMeetingBookFilePath());
        storage = new StorageManager(clientBookStorage, meetingBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s iscam book and {@code userPrefs}. <br>
     * The data from the sample iscam book will be used instead if {@code storage}'s iscam book is not found,
     * or an empty iscam book will be used instead if errors occur when reading {@code storage}'s iscam book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyClientBook> clientBookOptional;
        ReadOnlyClientBook initialClientBook;

        Optional<ReadOnlyMeetingBook> meetingBookOptional;
        ReadOnlyMeetingBook initialMeetingBook;

        //initialMeetingBook = new MeetingBook();

        try {
            clientBookOptional = storage.readClientBook();
            meetingBookOptional = storage.readMeetingBook();

            if (!clientBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample ClientBook");
            }
            if (!meetingBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample MeetingBook");
            }
            logger.info("Data file found. Loading ClientBook and MeetingBook");

            initialClientBook = clientBookOptional.orElseGet(SampleDataUtil::getSampleClientBook);
            initialMeetingBook = meetingBookOptional.orElseGet(SampleDataUtil::getSampleMeetingBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with "
                    + "an empty ClientBook and MeetingBook");
            initialClientBook = new ClientBook();
            initialMeetingBook = new MeetingBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with "
                    + "an empty ClientBook and MeetingBook");
            initialClientBook = new ClientBook();
            initialMeetingBook = new MeetingBook();
        }
        return new ModelManager(initialClientBook, initialMeetingBook, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty ClientBook");
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
        logger.info("Starting ClientBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Location Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
