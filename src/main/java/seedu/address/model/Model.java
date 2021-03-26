package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.ReadOnlyDishBook;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.ReadOnlyIngredientBook;
import seedu.address.model.order.Order;
import seedu.address.model.order.ReadOnlyOrderBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPersonBook;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPersonBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPersonBookFilePath(Path personBookFilePath);

    //=========== AddressBook ================================================================================

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyPersonBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyPersonBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns the {@code Person} object at the specified index on the UI
     */
    Person getPersonByIndex(int i);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== DishBook ================================================================================

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setDishBook(ReadOnlyDishBook dishBook);

    /** Returns the AddressBook */
    ReadOnlyDishBook getDishBook();

    /**
     * Returns true if a dish with the same name as {@code dish} exists in the address book.
     */
    boolean hasDish(Dish dish);

    /**
     * Deletes the given dish.
     * The dish must exist.
     */
    void deleteDish(Dish dish);

    /**
     * Adds the given dish.
     * {@code dish} must not already exist
     */
    void addDish(Dish dish);

    /**
     * Replaces the given dish {@code target} with {@code editedDish}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedDish} must not be the same as another existing dish in the address book.
     */
    void setDish(Dish target, Dish editedDish);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Dish> getFilteredDishList();

    /** Returns a list of dishes that use a particular ingredient */
    List<Dish> getDishesByIngredients(Ingredient ingredient);

    //=========== IngredientBook ================================================================================
    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setIngredientBook(ReadOnlyIngredientBook ingredientBook);

    /** Returns the AddressBook */
    ReadOnlyIngredientBook getIngredientBook();

    /**
     * Returns true if ingredient with the same name as {@code ingredient} exists in the address book.
     */
    boolean hasIngredient(Ingredient ingredient);

    /**
     * Deletes the given ingredient.
     * The ingredient must exist.
     */
    void deleteIngredient(Ingredient ingredient);

    /**
     * Adds the given ingredient.
     * {@code ingredient} must not already exist in the address book.
     */
    void addIngredient(Ingredient ingredient);

    /**
     * Replaces the given ingredient {@code target} with {@code editedIngredient}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedIngredient} must not be the same as another existing ingredient.
     */
    void setIngredient(Ingredient target, Ingredient editedIngredient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Ingredient> getFilteredIngredientList();

    //=========== OrderBook ================================================================================
    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setOrderBook(ReadOnlyOrderBook orderBook);

    /** Returns the AddressBook */
    ReadOnlyOrderBook getOrderBook();

    /**
     * Returns true if a dish with the same name as {@code dish} exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * The order must exist.
     */
    void deleteOrder(Order order);

    /**
     * Deletes a list of orders.
     * The orders must exist.
     */
    void deleteOrders(List<Order> orders);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addOrder(Order order);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setOrder(Order target, Order editedOrder);

    /** Returns an unmodifiable view of the filtered order list */
    ObservableList<Order> getFilteredOrderList();

    /** Returns an list of the orders belonging to a particular customer */
    List<Order> getOrdersFromPerson(Person target);
}
