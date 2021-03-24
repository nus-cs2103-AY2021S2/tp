package seedu.us.among;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.us.among.commons.core.Config;
import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.core.Version;
import seedu.us.among.commons.exceptions.DataConversionException;
import seedu.us.among.commons.util.ConfigUtil;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.Logic;
import seedu.us.among.logic.LogicManager;
import seedu.us.among.model.EndpointList;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.ReadOnlyUserPrefs;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.util.SampleDataUtil;
import seedu.us.among.storage.EndpointListStorage;
import seedu.us.among.storage.JsonEndpointListStorage;
import seedu.us.among.storage.JsonUserPrefsStorage;
import seedu.us.among.storage.Storage;
import seedu.us.among.storage.StorageManager;
import seedu.us.among.storage.UserPrefsStorage;
import seedu.us.among.ui.Ui;
import seedu.us.among.ui.UiManager;

/**
 * Runs the application.
 */
public class ImPoster extends Application {

    public static final Version VERSION = new Version(1, 2, 1, true);

    private static final Logger logger = LogsCenter.getLogger(ImPoster.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing imPoster ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        EndpointListStorage endpointListStorage = new JsonEndpointListStorage(userPrefs.getEndpointListFilePath());
        storage = new StorageManager(endpointListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s API endpoint list and {@code userPrefs}. <br>
     * The data from the sample API endpoint list will be used instead if {@code storage}'s API endpoint list is not
     * found, or an empty API endpoint list will be used instead if errors occur when reading {@code storage}'s API
     * endpoint list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyEndpointList> endpointListOptional;
        ReadOnlyEndpointList initialData;
        try {
            endpointListOptional = storage.readEndpointList();
            if (!endpointListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample EndpointList");
            }
            initialData = endpointListOptional.orElseGet(SampleDataUtil::getSampleEndpointList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty EndpointList");
            initialData = new EndpointList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty EndpointList");
            initialData = new EndpointList();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty EndpointList");
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
        logger.info("Starting EndpointList " + ImPoster.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping imPoster ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
