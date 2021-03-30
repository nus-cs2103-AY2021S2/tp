package seedu.storemando;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.storemando.commons.core.Config;
import seedu.storemando.commons.core.LogsCenter;
import seedu.storemando.commons.core.Version;
import seedu.storemando.commons.exceptions.DataConversionException;
import seedu.storemando.commons.util.ConfigUtil;
import seedu.storemando.commons.util.StringUtil;
import seedu.storemando.logic.Logic;
import seedu.storemando.logic.LogicManager;
import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.ReadOnlyUserPrefs;
import seedu.storemando.model.StoreMando;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.util.SampleDataUtil;
import seedu.storemando.storage.JsonStoreMandoStorage;
import seedu.storemando.storage.JsonUserPrefsStorage;
import seedu.storemando.storage.Storage;
import seedu.storemando.storage.StorageManager;
import seedu.storemando.storage.StoreMandoStorage;
import seedu.storemando.storage.UserPrefsStorage;
import seedu.storemando.ui.Ui;
import seedu.storemando.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing StoreMando ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        StoreMandoStorage storeMandoStorage = new JsonStoreMandoStorage(userPrefs.getStoreMandoFilePath());
        storage = new StorageManager(storeMandoStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s storemando and {@code userPrefs}. <br>
     * The data from the sample storemando will be used instead if {@code storage}'s storemando is not found,
     * or an empty storemando will be used instead if errors occur when reading {@code storage}'s storemando.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyStoreMando> storeMandoOptional;
        ReadOnlyStoreMando initialData;
        try {
            storeMandoOptional = storage.readStoreMando();
            if (storeMandoOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample StoreMando");
            }
            initialData = storeMandoOptional.orElseGet(SampleDataUtil::getSampleStoreMando);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty StoreMando");
            initialData = new StoreMando();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty StoreMando");
            initialData = new StoreMando();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty StoreMando");
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
        logger.info("Starting StoreMando " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping StoreMando ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
