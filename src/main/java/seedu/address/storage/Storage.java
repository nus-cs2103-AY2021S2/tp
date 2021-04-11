package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage {

    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //========= Address Book ========
    public Path getPersonBookFilePath();

    public Optional<ReadOnlyBook<Person>> readPersonBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyBook<Person>> readPersonBook(Path filePath) throws DataConversionException, IOException;

    public void savePersonBook(ReadOnlyBook<Person> addressBook) throws IOException;

    public void savePersonBook(ReadOnlyBook<Person> addressBook, Path filePath) throws IOException;

    // ================ DishBook methods ==============================
    public Path getDishBookFilePath();

    public Optional<ReadOnlyBook<Dish>> readDishBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyBook<Dish>> readDishBook(Path filePath) throws DataConversionException, IOException;

    public void saveDishBook(ReadOnlyBook<Dish> addressBook) throws IOException;

    public void saveDishBook(ReadOnlyBook<Dish> dishBook, Path filePath) throws IOException;

    // ================ IngredientBook methods ==============================
    public Path getIngredientBookFilePath();

    public Optional<ReadOnlyBook<Ingredient>> readIngredientBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyBook<Ingredient>> readIngredientBook(Path filePath)
            throws DataConversionException, IOException;

    public void saveIngredientBook(ReadOnlyBook<Ingredient> addressBook) throws IOException;

    public void saveIngredientBook(ReadOnlyBook<Ingredient> ingredientBook, Path filePath) throws IOException;

    // ================ OrderBook methods ==============================
    public Path getOrderBookFilePath();

    public Optional<ReadOnlyBook<Order>> readOrderBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyBook<Order>> readOrderBook(Path filePath) throws DataConversionException, IOException;

    public void saveOrderBook(ReadOnlyBook<Order> addressBook) throws IOException;

    public void saveOrderBook(ReadOnlyBook<Order> orderBook, Path filePath) throws IOException;
}
