package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

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

    //=========== PersonBook ================================================================================

    /**
     * Replaces person book data with the data in {@code personBook}.
     */
    void setPersonBook(ReadOnlyBook<Person> personBook);

    /** Returns the AddressBook */
    ReadOnlyBook<Person> getPersonBook();

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
     * @param predicate predicate to filter persons by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<? super Person> predicate);

    //=========== DishBook ================================================================================

    /**
     * Replaces dish book data with the data in {@code addressBook}.
     */
    void setDishBook(ReadOnlyBook<Dish> dishBook);

    /** Returns the DishBook */
    ReadOnlyBook<Dish> getDishBook();

    /**
     * Returns true if a dish with the same name as {@code dish} exists in the dish book.
     */
    boolean hasDish(Dish dish);

    /**
     * Returns the {@code Dish} object at the specified index on the UI
     */
    Dish getDishByIndex(int i);

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

    /** Returns an unmodifiable view of the filtered dish list */
    ObservableList<Dish> getFilteredDishList();

    /**
     * Updates the filter of the filtered dish list to filter by the given {@code predicate}.
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDishList(Predicate<? super Dish> predicate);

    /** Returns a list of dishes that use a particular ingredient */
    List<Dish> getDishesByIngredients(Ingredient ingredient);


    /**
     * Returns a predicate that returns true if a given dish can be produced (ie sufficient stock exists)
     */
    Predicate<Dish> getAvailableDishPredicate();

    /** Returns true if there is sufficient inventory to produce a given dish */
    boolean hasSufficientIngredients(Dish target);

    /** Returns true if there is sufficient inventory to produce {@code quantity} amount of given dish */
    boolean hasSufficientIngredients(Dish target, int quantity);

    //=========== IngredientBook ================================================================================
    /**
     * Replaces ingredient book data with the data in {@code ingredientBook}.
     */
    void setIngredientBook(ReadOnlyBook<Ingredient> ingredientBook);

    /** Returns the AddressBook */
    ReadOnlyBook<Ingredient> getIngredientBook();

    /**
     * Returns true if ingredient with the same name as {@code ingredient} exists in the address book.
     */
    boolean hasIngredient(Ingredient ingredient);

    /**
     * Returns the {@code Ingredient} object at the specified index on the UI
     */
    Ingredient getIngredientByIndex(int i);

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

    /**
     * Decrease the ingredient quantity by given quantity
     * @param target ingredient to be decreased
     * @param decreaseQuantity the number to decrease the ingredient quantity
     */
    public void decreaseIngredient(Ingredient target, int decreaseQuantity);

    /**
     * Decrease the ingredient quantity with the given {@code order}
     * @param order order added
     */
    void decreaseIngredientByOrder(Order order);

    /**
     * Increases an existing ingredient by given quantity
     * @param name existing ingredient's name
     * @param increaseQuantity the number to increase the ingredient quantity
     */
    void increaseIngredientByName(String name, int increaseQuantity);

    /**
     * Increase the ingredient quantity with the given {@code order}
     * @param order order deleted
     */
    void increaseIngredientByOrder(Order order);

    /** Returns an unmodifiable view of the filtered ingredient list */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<? super Ingredient> predicate);

    //=========== OrderBook ================================================================================
    /**
     * Replaces order book data with the data in {@code orderBook}.
     */
    void setOrderBook(ReadOnlyBook<Order> orderBook);

    /** Returns the OrderBook */
    ReadOnlyBook<Order> getOrderBook();

    /**
     * Returns true if a order exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * @param order to delete
     */
    void deleteOrder(Order order);

    /**
     * Deletes a {@code List<Order>} of orders
     * @param orders to delete
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

    //@@ author kangtinglee
    /** Returns a list of orders that have not been fulfilled and contain a given dish */
    List<Order> getIncompleteOrdersContainingDish(Dish target);

    //@@ author kangtinglee
    /** Returns a list of orders that contain a given dish */
    List<Order> getOrdersContainingDish(Dish target);

    /** Returns a list of orders that have not been fulfilled */
    List<Order> getIncompleteOrders();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<? super Order> predicate);

    /**
     * Updates the filtered order list by sorting it with {@code comparator}.
     * @param comparator
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Comparator<Order> comparator);

    //@@ author kangtinglee
    /** Returns an list of the orders belonging to a particular customer */
    List<Order> getOrdersFromPerson(Person target);

    //@@ author kangtinglee
    /** Returns an list of the incomplete orders belonging to a particular customer */
    List<Order> getIncompleteOrdersFromPerson(Person target);

    /**
     * Sets the state of the order to cancelled
     */
    void cancelOrder(Order target);

    /**
     * Sets the state of the orders to cancelled
     */
    void cancelOrders(List<Order> targets);

    /** Returns true if an order can be fulfilled */
    boolean canFulfilOrder(Order target);

    /** Returns true if a list of orders can be fulfilled */
    boolean canFulfilOrders(List<Order> orders);
}
