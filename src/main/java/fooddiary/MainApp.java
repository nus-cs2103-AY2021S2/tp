package fooddiary;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import fooddiary.commons.core.Config;
import fooddiary.commons.core.LogsCenter;
import fooddiary.commons.core.Version;
import fooddiary.commons.exceptions.DataConversionException;
import fooddiary.commons.util.ConfigUtil;
import fooddiary.commons.util.StringUtil;
import fooddiary.logic.Logic;
import fooddiary.logic.LogicManager;
import fooddiary.model.FoodDiary;
import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.ReadOnlyFoodDiary;
import fooddiary.model.ReadOnlyUserPrefs;
import fooddiary.model.UserPrefs;
import fooddiary.model.util.SampleDataUtil;
import fooddiary.storage.FoodDiaryStorage;
import fooddiary.storage.JsonFoodDiaryStorage;
import fooddiary.storage.JsonUserPrefsStorage;
import fooddiary.storage.Storage;
import fooddiary.storage.StorageManager;
import fooddiary.storage.UserPrefsStorage;
import fooddiary.ui.Ui;
import fooddiary.ui.UiManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        logger.info("=============================[ Initializing FoodDiary ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FoodDiaryStorage foodDiaryStorage = new JsonFoodDiaryStorage(userPrefs.getFoodDiaryFilePath());
        storage = new StorageManager(foodDiaryStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s food diary and {@code userPrefs}. <br>
     * The data from the sample food diary will be used instead if {@code storage}'s food diary is not found,
     * or an empty food diary will be used instead if errors occur when reading {@code storage}'s food diary.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFoodDiary> foodDiaryOptional;
        ReadOnlyFoodDiary initialData;
        try {
            foodDiaryOptional = storage.readFoodDiary();
            if (!foodDiaryOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample FoodDiary");
            }
            initialData = foodDiaryOptional.orElseGet(SampleDataUtil::getSampleFoodDiary);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty FoodDiary");
            initialData = new FoodDiary();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FoodDiary");
            initialData = new FoodDiary();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty FoodDiary");
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
        logger.info("Starting FoodDiary " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Food Diary ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
