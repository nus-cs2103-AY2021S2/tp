package seedu.cakecollate;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.cakecollate.commons.core.Config;
import seedu.cakecollate.commons.core.LogsCenter;
import seedu.cakecollate.commons.core.Version;
import seedu.cakecollate.commons.exceptions.DataConversionException;
import seedu.cakecollate.commons.util.ConfigUtil;
import seedu.cakecollate.commons.util.StringUtil;
import seedu.cakecollate.logic.Logic;
import seedu.cakecollate.logic.LogicManager;
import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.ReadOnlyCakeCollate;
import seedu.cakecollate.model.ReadOnlyOrderItems;
import seedu.cakecollate.model.ReadOnlyUserPrefs;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.util.SampleDataUtil;
import seedu.cakecollate.storage.CakeCollateStorage;
import seedu.cakecollate.storage.JsonCakeCollateStorage;
import seedu.cakecollate.storage.JsonOrderItemsStorage;
import seedu.cakecollate.storage.JsonUserPrefsStorage;
import seedu.cakecollate.storage.OrderItemsStorage;
import seedu.cakecollate.storage.Storage;
import seedu.cakecollate.storage.StorageManager;
import seedu.cakecollate.storage.UserPrefsStorage;
import seedu.cakecollate.ui.Ui;
import seedu.cakecollate.ui.UiManager;

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
        logger.info("=============================[ Initializing CakeCollate ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        CakeCollateStorage cakeCollateStorage = new JsonCakeCollateStorage(userPrefs.getCakeCollateFilePath());
        OrderItemsStorage orderItemsStorage = new JsonOrderItemsStorage(userPrefs.getOrderItemsFilePath());
        storage = new StorageManager(cakeCollateStorage, userPrefsStorage, orderItemsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s cakecollate and {@code userPrefs}. <br>
     * The data from the sample cakecollate will be used instead if {@code storage}'s cakecollate is not found,
     * or an empty cakecollate will be used instead if errors occur when reading {@code storage}'s cakecollate.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyOrderItems> orderItemsOptional;
        Optional<ReadOnlyCakeCollate> cakeCollateOptional;
        ReadOnlyCakeCollate initialData;
        ReadOnlyOrderItems initialDataForOrderItems;
        try {
            cakeCollateOptional = storage.readCakeCollate();
            orderItemsOptional = storage.readOrderItems();
            if (!cakeCollateOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample CakeCollate");
            }
            initialData = cakeCollateOptional.orElseGet(SampleDataUtil::getSampleCakeCollate);
            initialDataForOrderItems = orderItemsOptional.orElseGet(SampleDataUtil::getSampleOrderItems);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty CakeCollate");
            initialData = new CakeCollate();
            initialDataForOrderItems = new OrderItems();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty CakeCollate");
            initialData = new CakeCollate();
            initialDataForOrderItems = new OrderItems();
        }

        return new ModelManager(initialData, userPrefs, initialDataForOrderItems);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty CakeCollate");
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
        logger.info("Starting CakeCollate " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Cake Collate ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
