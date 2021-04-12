package seedu.heymatez;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.heymatez.commons.core.Config;
import seedu.heymatez.commons.core.LogsCenter;
import seedu.heymatez.commons.core.Version;
import seedu.heymatez.commons.exceptions.DataConversionException;
import seedu.heymatez.commons.util.ConfigUtil;
import seedu.heymatez.commons.util.StringUtil;
import seedu.heymatez.logic.Logic;
import seedu.heymatez.logic.LogicManager;
import seedu.heymatez.model.HeyMatez;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.ReadOnlyUserPrefs;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.util.SampleDataUtil;
import seedu.heymatez.storage.HeyMatezStorage;
import seedu.heymatez.storage.JsonHeyMatezStorage;
import seedu.heymatez.storage.JsonUserPrefsStorage;
import seedu.heymatez.storage.Storage;
import seedu.heymatez.storage.StorageManager;
import seedu.heymatez.storage.UserPrefsStorage;
import seedu.heymatez.ui.Ui;
import seedu.heymatez.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 4, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing HeyMatez ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        HeyMatezStorage heyMatezStorage = new JsonHeyMatezStorage(userPrefs.getHeyMatezFilePath());
        storage = new StorageManager(heyMatezStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s Hey Matez and {@code userPrefs}. <br>
     * The data from the sample Hey Matez will be used instead if {@code storage}'s Hey Matez  is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s Hey Matez .
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyHeyMatez> heyMatezOptional;
        ReadOnlyHeyMatez initialData;
        try {
            heyMatezOptional = storage.readHeyMatez();
            if (!heyMatezOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample HeyMatez");
            }
            initialData = heyMatezOptional.orElseGet(SampleDataUtil::getSampleHeyMatez);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty HeyMatez");
            initialData = new HeyMatez();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty HeyMatez");
            initialData = new HeyMatez();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty HeyMatez");
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
        logger.info("Starting HeyMatez " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping HeyMatez ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
