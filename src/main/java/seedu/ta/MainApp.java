package seedu.ta;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.ta.commons.core.Config;
import seedu.ta.commons.core.LogsCenter;
import seedu.ta.commons.core.Version;
import seedu.ta.commons.exceptions.DataConversionException;
import seedu.ta.commons.util.ConfigUtil;
import seedu.ta.commons.util.StringUtil;
import seedu.ta.logic.Logic;
import seedu.ta.logic.LogicManager;
import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;
import seedu.ta.model.ReadOnlyTeachingAssistant;
import seedu.ta.model.ReadOnlyUserPrefs;
import seedu.ta.model.TeachingAssistant;
import seedu.ta.model.UserPrefs;
import seedu.ta.model.util.SampleDataUtil;
import seedu.ta.storage.JsonTeachingAssistantStorage;
import seedu.ta.storage.JsonUserPrefsStorage;
import seedu.ta.storage.Storage;
import seedu.ta.storage.StorageManager;
import seedu.ta.storage.TeachingAssistantStorage;
import seedu.ta.storage.UserPrefsStorage;
import seedu.ta.ui.Ui;
import seedu.ta.ui.UiManager;

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
        logger.info("=============================[ Initializing TeachingAssistant ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        TeachingAssistantStorage teachingAssistantStorage =
                new JsonTeachingAssistantStorage(userPrefs.getTeachingAssistantFilePath());
        storage = new StorageManager(teachingAssistantStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s teaching assistant and {@code userPrefs}.
     * <br> The data from the sample teaching assistant will be used instead if {@code storage}'s teaching assistant
     * is not found, or an empty teaching assistant will be used instead if errors occur when reading {@code storage}'s
     * teaching assistant.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyTeachingAssistant> teachingAssistantOptional;
        ReadOnlyTeachingAssistant initialData;
        try {
            teachingAssistantOptional = storage.readTeachingAssistant();
            if (!teachingAssistantOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample TeachingAssistant");
            }
            initialData = teachingAssistantOptional.orElseGet(SampleDataUtil::getSampleTeachingAssistant);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty TeachingAssistant");
            initialData = new TeachingAssistant();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty TeachingAssistant");
            initialData = new TeachingAssistant();
        }

        return new ModelManager(initialData, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty TeachingAssistant");
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
        logger.info("Starting TeachingAssistant " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Teaching Assistant ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
