package seedu.timeforwheels;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.timeforwheels.commons.core.Config;
import seedu.timeforwheels.commons.core.LogsCenter;
import seedu.timeforwheels.commons.core.Version;
import seedu.timeforwheels.commons.exceptions.DataConversionException;
import seedu.timeforwheels.commons.util.ConfigUtil;
import seedu.timeforwheels.commons.util.StringUtil;
import seedu.timeforwheels.logic.Logic;
import seedu.timeforwheels.logic.LogicManager;
import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.ReadOnlyUserPrefs;
import seedu.timeforwheels.model.UserPrefs;
import seedu.timeforwheels.model.util.SampleDataUtil;
import seedu.timeforwheels.storage.DeliveryListStorage;
import seedu.timeforwheels.storage.JsonDeliveryListStorage;
import seedu.timeforwheels.storage.JsonUserPrefsStorage;
import seedu.timeforwheels.storage.Storage;
import seedu.timeforwheels.storage.StorageManager;
import seedu.timeforwheels.storage.UserPrefsStorage;
import seedu.timeforwheels.ui.Ui;
import seedu.timeforwheels.ui.UiManager;

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
        logger.info("=============================[ Initializing DeliveryList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        DeliveryListStorage deliveryListStorage = new JsonDeliveryListStorage(userPrefs.getDeliveryListFilePath());
        storage = new StorageManager(deliveryListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s delivery list and {@code userPrefs}. <br>
     * The data from the sample delivery list will be used instead if {@code storage}'s delivery list is not found,
     * or an empty delivery list will be used instead if errors occur when reading {@code storage}'s delivery list.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyDeliveryList> deliveryListOptional;
        ReadOnlyDeliveryList initialData;
        try {
            deliveryListOptional = storage.readDeliveryList();
            if (!deliveryListOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample DeliveryList");
            }
            initialData = deliveryListOptional.orElseGet(SampleDataUtil::getSampleDeliveryList);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty DeliveryList");
            initialData = new DeliveryList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty DeliveryList");
            initialData = new DeliveryList();
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
            logger.warning("Problem while reading from the file. Will be starting with an empty DeliveryList");
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
        logger.info("Starting DeliveryList " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Delivery List ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
