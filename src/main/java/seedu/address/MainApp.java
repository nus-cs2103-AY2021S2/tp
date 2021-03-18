package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.diet.MacroNutrientComposition;
import seedu.address.model.diet.PlanType;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.DietPlanListStorage;
import seedu.address.storage.FoodIntakeListStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonDietPlanListStorage;
import seedu.address.storage.JsonFoodIntakeListStorage;
import seedu.address.storage.JsonUniqueFoodListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UniqueFoodListStorage;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

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
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        UniqueFoodListStorage uniqueFoodListStorage =
                new JsonUniqueFoodListStorage(userPrefs.getUniqueFoodListFilePath());
        FoodIntakeListStorage foodIntakeListStorage =
                new JsonFoodIntakeListStorage(userPrefs.getFoodIntakeListFilePath());
        DietPlanListStorage dietPlanListStorage = new JsonDietPlanListStorage(userPrefs.getDietPlanListFilePath());
        storage = new StorageManager(addressBookStorage, uniqueFoodListStorage,
                foodIntakeListStorage, dietPlanListStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        // TODO: Update handling of method
        Optional<ReadOnlyAddressBook> addressBookOptional;
        ReadOnlyAddressBook initialData;
        Optional<UniqueFoodList> uniqueFoodListOptional;
        UniqueFoodList uniqueFoodList;
        Optional<FoodIntakeList> foodIntakeListOptional;
        FoodIntakeList foodIntakeList;
        Optional<DietPlanList> dietPlanListOptional;
        DietPlanList dietPlanList;

        try {
            addressBookOptional = storage.readAddressBook();
            uniqueFoodListOptional = storage.readFoodList();
            foodIntakeListOptional = storage.readFoodIntakeList();
            dietPlanListOptional = storage.readDietPlanList();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            if (!uniqueFoodListOptional.isPresent()) {
                logger.info("Food data file not found. Will be starting fresh");
            }
            if (!foodIntakeListOptional.isPresent()) {
                logger.info("Food intake data file not found. Will be starting fresh");
            }
            if (!dietPlanListOptional.isPresent()) {
                logger.info("Diet plans file not found. Will be starting fresh");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
            uniqueFoodList = uniqueFoodListOptional.orElse(new UniqueFoodList());
            foodIntakeList = foodIntakeListOptional.orElse(new FoodIntakeList());

            //dietPlanList = dietPlanListOptional.orElse(new DietPlanList());
            // TODO Implement reading of diet plans list from file
            // Using dummy data now for testing
            MacroNutrientComposition dummyMacros = new MacroNutrientComposition(20, 10, 70);
            DietPlan dummyPlan = new DietPlan("DummyPlan", "This is a dummy plan.", dummyMacros,
                    PlanType.WEIGHTGAIN);

            dietPlanList = new DietPlanList();
            dietPlanList.addDietPlan(dummyPlan);

        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
            uniqueFoodList = new UniqueFoodList();
            foodIntakeList = new FoodIntakeList();
            dietPlanList = new DietPlanList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
            uniqueFoodList = new UniqueFoodList();
            foodIntakeList = new FoodIntakeList();
            dietPlanList = new DietPlanList();
        }

        return new ModelManager(initialData, uniqueFoodList, foodIntakeList, dietPlanList, userPrefs);
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
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
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
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
