package seedu.flashback;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.flashback.commons.core.Config;
import seedu.flashback.commons.core.LogsCenter;
import seedu.flashback.commons.core.Version;
import seedu.flashback.commons.exceptions.DataConversionException;
import seedu.flashback.commons.util.ConfigUtil;
import seedu.flashback.commons.util.StringUtil;
import seedu.flashback.logic.Logic;
import seedu.flashback.logic.LogicManager;
import seedu.flashback.model.AliasMap;
import seedu.flashback.model.FlashBack;
import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.ReadOnlyUserPrefs;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.util.SampleDataUtil;
import seedu.flashback.storage.FlashBackStorage;
import seedu.flashback.storage.JsonFlashBackStorage;
import seedu.flashback.storage.JsonUserPrefsStorage;
import seedu.flashback.storage.Storage;
import seedu.flashback.storage.StorageManager;
import seedu.flashback.storage.UserPrefsStorage;
import seedu.flashback.ui.Ui;
import seedu.flashback.ui.UiManager;

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
        logger.info("=============================[ Initializing FlashBack ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FlashBackStorage flashBackStorage = new JsonFlashBackStorage(userPrefs.getFlashBackFilePath());
        storage = new StorageManager(flashBackStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s FlashBack and {@code userPrefs}. <br>
     * The data from the sample FlashBack will be used instead if {@code storage}'s FlashBack is not found,
     * or an empty FlashBack will be used instead if errors occur when reading {@code storage}'s FlashBack.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFlashBack> flashBackOptional;
        ReadOnlyFlashBack initialData;
        try {
            flashBackOptional = storage.readFlashBack();
            if (!flashBackOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample FlashBack");
            }
            initialData = flashBackOptional.orElseGet(SampleDataUtil::getSampleFlashBack);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty FlashBack");
            initialData = new FlashBack();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FlashBack");
            initialData = new FlashBack();
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
            if (initializedPrefs.isAliasMapCorrupted()) {
                logger.warning("AliasMap is corrupted. Will be starting with an empty AliasMap");
                initializedPrefs.setAliasMap(new AliasMap());
            }
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FlashBack");
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
        logger.info("Starting FlashBack " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping FlashBack ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
