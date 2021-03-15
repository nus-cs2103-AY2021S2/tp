package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.ReadOnlyDishBook;
import seedu.address.model.ingredient.ReadOnlyIngredientBook;
import seedu.address.model.order.ReadOnlyOrderBook;
import seedu.address.model.person.ReadOnlyPersonBook;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BookStorage<ReadOnlyPersonBook> personBookStorage;
    private BookStorage<ReadOnlyDishBook> dishBookStorage;
    private BookStorage<ReadOnlyIngredientBook> ingredientBookStorage;
    private BookStorage<ReadOnlyOrderBook> orderBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BookStorage<ReadOnlyPersonBook> personBookStorage,
                          BookStorage<ReadOnlyDishBook> dishBookStorage,
                          BookStorage<ReadOnlyIngredientBook> ingredientBookStorage,
                          BookStorage<ReadOnlyOrderBook> orderBookStorage,
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
    public Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException, IOException {
        return readPersonBook(personBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonBook> readPersonBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return personBookStorage.readBook(filePath);
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook addressBook) throws IOException {
        savePersonBook(addressBook, personBookStorage.getBookFilePath());
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        personBookStorage.saveBook(addressBook, filePath);
    }

    // ================ DishBook methods ==============================

    @Override
    public Path getDishBookFilePath() {
        return dishBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDishBook> readDishBook() throws DataConversionException, IOException {
        return readDishBook(dishBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDishBook> readDishBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return dishBookStorage.readBook(filePath);
    }

    @Override
    public void saveDishBook(ReadOnlyDishBook addressBook) throws IOException {
        saveDishBook(addressBook, dishBookStorage.getBookFilePath());
    }

    @Override
    public void saveDishBook(ReadOnlyDishBook dishBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dishBookStorage.saveBook(dishBook, filePath);
    }

    // ================ IngredientBook methods ==============================

    @Override
    public Path getIngredientBookFilePath() {
        return ingredientBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyIngredientBook> readIngredientBook() throws DataConversionException, IOException {
        return readIngredientBook(ingredientBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyIngredientBook> readIngredientBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ingredientBookStorage.readBook(filePath);
    }

    @Override
    public void saveIngredientBook(ReadOnlyIngredientBook addressBook) throws IOException {
        saveIngredientBook(addressBook, ingredientBookStorage.getBookFilePath());
    }

    @Override
    public void saveIngredientBook(ReadOnlyIngredientBook ingredientBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ingredientBookStorage.saveBook(ingredientBook, filePath);
    }

    // ================ OrderBook methods ==============================
    @Override
    public Path getOrderBookFilePath() {
        return orderBookStorage.getBookFilePath();
    }

    @Override
    public Optional<ReadOnlyOrderBook> readOrderBook() throws DataConversionException, IOException {
        return readOrderBook(orderBookStorage.getBookFilePath());
    }

    @Override
    public Optional<ReadOnlyOrderBook> readOrderBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return orderBookStorage.readBook(filePath);
    }

    @Override
    public void saveOrderBook(ReadOnlyOrderBook addressBook) throws IOException {
        saveOrderBook(addressBook, orderBookStorage.getBookFilePath());
    }

    @Override
    public void saveOrderBook(ReadOnlyOrderBook orderBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        orderBookStorage.saveBook(orderBook, filePath);
    }

}
