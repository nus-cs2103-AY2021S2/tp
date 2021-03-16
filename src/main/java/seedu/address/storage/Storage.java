package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.dish.ReadOnlyDishBook;
import seedu.address.model.ingredient.ReadOnlyIngredientBook;
import seedu.address.model.order.ReadOnlyOrderBook;
import seedu.address.model.person.ReadOnlyPersonBook;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage {

    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //========= Address Book ========
    public Path getPersonBookFilePath();

    public Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyPersonBook> readPersonBook(Path filePath) throws DataConversionException, IOException;

    public void savePersonBook(ReadOnlyPersonBook addressBook) throws IOException;

    public void savePersonBook(ReadOnlyPersonBook addressBook, Path filePath) throws IOException;

    // ================ DishBook methods ==============================
    public Path getDishBookFilePath();

    public Optional<ReadOnlyDishBook> readDishBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyDishBook> readDishBook(Path filePath) throws DataConversionException, IOException;

    public void saveDishBook(ReadOnlyDishBook addressBook) throws IOException;

    public void saveDishBook(ReadOnlyDishBook dishBook, Path filePath) throws IOException;

    // ================ IngredientBook methods ==============================
    public Path getIngredientBookFilePath();

    public Optional<ReadOnlyIngredientBook> readIngredientBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyIngredientBook> readIngredientBook(Path filePath)
            throws DataConversionException, IOException;

    public void saveIngredientBook(ReadOnlyIngredientBook addressBook) throws IOException;

    public void saveIngredientBook(ReadOnlyIngredientBook ingredientBook, Path filePath) throws IOException;

    // ================ OrderBook methods ==============================
    public Path getOrderBookFilePath();

    public Optional<ReadOnlyOrderBook> readOrderBook() throws DataConversionException, IOException;

    public Optional<ReadOnlyOrderBook> readOrderBook(Path filePath) throws DataConversionException, IOException;

    public void saveOrderBook(ReadOnlyOrderBook addressBook) throws IOException;

    public void saveOrderBook(ReadOnlyOrderBook orderBook, Path filePath) throws IOException;
}
