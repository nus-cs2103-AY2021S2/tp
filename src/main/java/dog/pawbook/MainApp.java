package dog.pawbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import dog.pawbook.commons.core.Config;
import dog.pawbook.commons.core.LogsCenter;
import dog.pawbook.commons.core.Version;
import dog.pawbook.commons.exceptions.DataConversionException;
import dog.pawbook.commons.util.ConfigUtil;
import dog.pawbook.commons.util.StringUtil;
import dog.pawbook.logic.Logic;
import dog.pawbook.logic.LogicManager;
import dog.pawbook.model.Database;
import dog.pawbook.model.Model;
import dog.pawbook.model.ModelManager;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.ReadOnlyUserPrefs;
import dog.pawbook.model.UserPrefs;
import dog.pawbook.model.util.SampleDataUtil;
import dog.pawbook.storage.DatabaseStorage;
import dog.pawbook.storage.JsonDatabaseStorage;
import dog.pawbook.storage.JsonUserPrefsStorage;
import dog.pawbook.storage.Storage;
import dog.pawbook.storage.StorageManager;
import dog.pawbook.storage.UserPrefsStorage;
import dog.pawbook.ui.Ui;
import dog.pawbook.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, false);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Database ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        DatabaseStorage databaseStorage = new JsonDatabaseStorage(userPrefs.getDatabaseFilePath());
        storage = new StorageManager(databaseStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s database and {@code userPrefs}. <br>
     * The data from the sample database will be used instead if {@code storage}'s database is not found,
     * or an empty database will be used instead if errors occur when reading {@code storage}'s database.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyDatabase> databaseOptional;
        ReadOnlyDatabase initialData;
        try {
            databaseOptional = storage.readDatabase();
            if (databaseOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample Database");
            }
            initialData = databaseOptional.orElseGet(SampleDataUtil::getSampleDatabase);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty Database");
            initialData = new Database();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty Database");
            initialData = new Database();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty Database");
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
        logger.info("Starting Database " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Database ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
