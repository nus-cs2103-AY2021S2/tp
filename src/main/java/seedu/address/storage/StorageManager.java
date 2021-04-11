package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BookStorage<Person> personBookStorage;
    private BookStorage<Dish> dishBookStorage;
    private BookStorage<Ingredient> ingredientBookStorage;
    private BookStorage<Order> orderBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BookStorage<Person> personBookStorage,
                          BookStorage<Dish> dishBookStorage,
                          BookStorage<Ingredient> ingredientBookStorage,
                          BookStorage<Order> orderBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.personBookStorage = personBookStorage;
        this.dishBookStorage = dishBookStorage;
        this.ingredientBookStorage = ingredientBookStorage;
        this.orderBookStorage = orderBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PersonBook methods ==============================

    @Override
    public Path getPersonBookFilePath() {
        return personBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBook<Person>> readPersonBook() throws DataConversionException, IOException {
        return readPersonBook(personBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBook<Person>> readPersonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return personBookStorage.readBook(filePath);
    }

    @Override
    public void savePersonBook(ReadOnlyBook<Person> addressBook) throws IOException {
        savePersonBook(addressBook, personBookStorage.getBookFilePath());
    }

    @Override
    public void savePersonBook(ReadOnlyBook<Person> addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        personBookStorage.saveBook(addressBook, filePath);
    }

    // ================ DishBook methods ==============================

    @Override
    public Path getDishBookFilePath() {
        return dishBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBook<Dish>> readDishBook() throws DataConversionException, IOException {
        return readDishBook(dishBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBook<Dish>> readDishBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return dishBookStorage.readBook(filePath);
    }

    @Override
    public void saveDishBook(ReadOnlyBook<Dish> addressBook) throws IOException {
        saveDishBook(addressBook, dishBookStorage.getBookFilePath());
    }

    @Override
    public void saveDishBook(ReadOnlyBook<Dish> dishBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dishBookStorage.saveBook(dishBook, filePath);
    }

    // ================ IngredientBook methods ==============================

    @Override
    public Path getIngredientBookFilePath() {
        return ingredientBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBook<Ingredient>> readIngredientBook() throws DataConversionException, IOException {
        return readIngredientBook(ingredientBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBook<Ingredient>> readIngredientBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ingredientBookStorage.readBook(filePath);
    }

    @Override
    public void saveIngredientBook(ReadOnlyBook<Ingredient> addressBook) throws IOException {
        saveIngredientBook(addressBook, ingredientBookStorage.getBookFilePath());
    }

    @Override
    public void saveIngredientBook(ReadOnlyBook<Ingredient> ingredientBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ingredientBookStorage.saveBook(ingredientBook, filePath);
    }

    // ================ OrderBook methods ==============================
    @Override
    public Path getOrderBookFilePath() {
        return orderBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBook<Order>> readOrderBook() throws DataConversionException, IOException {
        return readOrderBook(orderBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBook<Order>> readOrderBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return orderBookStorage.readBook(filePath);
    }

    @Override
    public void saveOrderBook(ReadOnlyBook<Order> addressBook) throws IOException {
        saveOrderBook(addressBook, orderBookStorage.getBookFilePath());
    }

    @Override
    public void saveOrderBook(ReadOnlyBook<Order> orderBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        orderBookStorage.saveBook(orderBook, filePath);
    }

}
