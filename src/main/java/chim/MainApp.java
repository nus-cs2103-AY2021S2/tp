package chim;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import chim.commons.core.Config;
import chim.commons.core.LogsCenter;
import chim.commons.core.Version;
import chim.commons.exceptions.DataConversionException;
import chim.commons.util.ConfigUtil;
import chim.commons.util.StringUtil;
import chim.logic.Logic;
import chim.logic.LogicManager;
import chim.model.Chim;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.ReadOnlyChim;
import chim.model.ReadOnlyUserPrefs;
import chim.model.UserPrefs;
import chim.model.util.SampleDataUtil;
import chim.storage.ChimStorage;
import chim.storage.JsonChimStorage;
import chim.storage.JsonUserPrefsStorage;
import chim.storage.Storage;
import chim.storage.StorageManager;
import chim.storage.UserPrefsStorage;
import chim.ui.Ui;
import chim.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        logger.info("========================[ Initializing Cheese Inventory Management ]========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ChimStorage chimStorage = new JsonChimStorage(userPrefs.getChimFilePath());
        storage = new StorageManager(chimStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Chim and {@code userPrefs}. <br>
     * The data from the sample Chim will be used instead if {@code storage}'s Chim is not found,
     * or an empty Chim will be used instead if errors occur when reading {@code storage}'s Chim.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyChim> chimOptional;
        ReadOnlyChim initialData;
        try {
            chimOptional = storage.readChim();
            if (!chimOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with sample data.");
            }
            initialData = chimOptional.orElseGet(SampleDataUtil::getSampleChim);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with no loaded data.");
            initialData = new Chim();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with no loaded data.");
            initialData = new Chim();
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
            logger.warning("Problem while reading from the file. Will be starting with no loaded data.");
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
        logger.info("Starting Cheese Inventory Management " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("======================== [ Stopping Cheese Inventory Management ] ========================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
