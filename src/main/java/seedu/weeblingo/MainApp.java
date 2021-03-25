package seedu.weeblingo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.weeblingo.commons.core.Config;
import seedu.weeblingo.commons.core.LogsCenter;
import seedu.weeblingo.commons.core.Version;
import seedu.weeblingo.commons.exceptions.DataConversionException;
import seedu.weeblingo.commons.util.ConfigUtil;
import seedu.weeblingo.commons.util.StringUtil;
import seedu.weeblingo.logic.Logic;
import seedu.weeblingo.logic.LogicManager;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.ReadOnlyUserPrefs;
import seedu.weeblingo.model.UserPrefs;
import seedu.weeblingo.storage.FlashcardBookStorage;
import seedu.weeblingo.storage.JsonFlashcardBookStorage;
import seedu.weeblingo.storage.JsonUserPrefsStorage;
import seedu.weeblingo.storage.LocalDatabasePopulator;
import seedu.weeblingo.storage.Storage;
import seedu.weeblingo.storage.StorageManager;
import seedu.weeblingo.storage.UserPrefsStorage;
import seedu.weeblingo.ui.Ui;
import seedu.weeblingo.ui.UiManager;

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
        logger.info("=============================[ Initializing FlashcardBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FlashcardBookStorage flashcardBookStorage = new JsonFlashcardBookStorage(userPrefs.getFlashcardBookFilePath());
        storage = new StorageManager(flashcardBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s flashcard book and {@code userPrefs}. <br>
     * The data from the sample flashcard book will be used instead if {@code storage}'s flashcard book is not found,
     * or an empty flashcard book will be used instead if errors occur when reading {@code storage}'s flashcard book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFlashcardBook> flashcardOptional;
        ReadOnlyFlashcardBook initialData;
        try {
            flashcardOptional = storage.readFlashcardBook();
            if (!flashcardOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample FlashcardBook");
            }

            initialData = flashcardOptional.orElseGet(LocalDatabasePopulator::getDatabaseOfWeeblingo);
            logger.info(String.format("Weeblingo database succesfully populated: %s", initialData.toString()));
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty FlashcardBook");
            initialData = new FlashcardBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FlashcardBook");
            initialData = new FlashcardBook();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty FlashcardBook");
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
        logger.info("Starting FlashcardBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Flashcard Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
