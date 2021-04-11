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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.DishBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.BookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.dish.JsonDishBookStorage;
import seedu.address.storage.ingredient.JsonIngredientBookStorage;
import seedu.address.storage.order.JsonOrderBookStorage;
import seedu.address.storage.person.JsonPersonBookStorage;
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
        BookStorage<Person> addressBookStorage =
                new JsonPersonBookStorage(userPrefs.getPersonBookFilePath());
        BookStorage<Dish> dishBookStorage =
                new JsonDishBookStorage(userPrefs.getDishBookFilePath());
        BookStorage<Ingredient> ingredientBookStorage =
                new JsonIngredientBookStorage(userPrefs.getIngredientBookFilePath());
        BookStorage<Order> orderBookStorage =
                new JsonOrderBookStorage(userPrefs.getOrderBookFilePath());
        storage = new StorageManager(addressBookStorage, dishBookStorage,
                ingredientBookStorage, orderBookStorage, userPrefsStorage);

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
        ReadOnlyBook<Person> initialPersonBook;
        ReadOnlyBook<Dish> initialDishBook;
        ReadOnlyBook<Ingredient> initialIngredientBook;
        ReadOnlyBook<Order> initialOrderBook;

        try {
            Optional<ReadOnlyBook<Person>> personBookOptional = storage.readPersonBook();
            Optional<ReadOnlyBook<Dish>> dishBookOptional = storage.readDishBook();
            Optional<ReadOnlyBook<Ingredient>> ingredientBookOptional = storage.readIngredientBook();
            Optional<ReadOnlyBook<Order>> orderBookOptional = storage.readOrderBook();
            if (!personBookOptional.isPresent()
                    || !dishBookOptional.isPresent()
                    || !ingredientBookOptional.isPresent()
                    || !orderBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample data for all books.");
            }
            initialPersonBook = personBookOptional.orElseGet(SampleDataUtil::getSamplePersonBook);
            initialDishBook = dishBookOptional.orElseGet(SampleDataUtil::getSampleDishBook);
            initialIngredientBook = ingredientBookOptional.orElseGet(SampleDataUtil::getSampleIngredientBook);
            initialOrderBook = orderBookOptional.orElseGet(SampleDataUtil::getSampleOrderBook);
        } catch (DataConversionException e) {
            logger.warning("One of the data files not in the correct format. Will be starting with empty books");
            initialPersonBook = new PersonBook();
            initialDishBook = new DishBook();
            initialIngredientBook = new IngredientBook();
            initialOrderBook = new OrderBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with empty books");
            initialPersonBook = new PersonBook();
            initialDishBook = new DishBook();
            initialIngredientBook = new IngredientBook();
            initialOrderBook = new OrderBook();
        }

        return new ModelManager(initialPersonBook, initialDishBook, initialIngredientBook, initialOrderBook, userPrefs);
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
