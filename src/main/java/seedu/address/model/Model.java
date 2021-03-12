package seedu.address.model;

import java.nio.file.Path;
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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

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

//    /**
//     * Returns true if a dish with the same name as {@code dish} exists in the address book.
//     */
//    boolean hasDish(Dish dish);
//
//    /**
//     * Deletes the given dish.
//     * The dish must exist.
//     */
//    void deleteDish(Dish dish);
//
//    /**
//     * Adds the given person.
//     * {@code person} must not already exist in the address book.
//     */
//    void addDish(Dish dish);
//
//    /**
//     * Replaces the given person {@code target} with {@code editedPerson}.
//     * {@code target} must exist in the address book.
//     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
//     */
//    void setDish(Dish target, Dish editedDish);
//
//    /** Returns an unmodifiable view of the filtered person list */
//    ObservableList<Dish> getFilteredDishList();
//
//    /**
//     * Returns true if a dish with the same name as {@code dish} exists in the address book.
//     */
//    boolean hasIngredient(Ingredient ingredient);
//
//    /**
//     * Deletes the given dish.
//     * The dish must exist.
//     */
//    void deleteIngredient(Ingredient ingredient);
//
//    /**
//     * Adds the given person.
//     * {@code person} must not already exist in the address book.
//     */
//    void addIngredient(Ingredient ingredient);
//
//    /**
//     * Replaces the given person {@code target} with {@code editedPerson}.
//     * {@code target} must exist in the address book.
//     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
//     */
//    void setIngredient(Ingredient target, Ingredient editedIngredient);
//
//    /** Returns an unmodifiable view of the filtered person list */
//    ObservableList<Ingredient> getFilteredIngredientList();
//
//    /**
//     * Returns true if a dish with the same name as {@code dish} exists in the address book.
//     */
//    boolean hasOrder(Order order);
//
//    /**
//     * Deletes the given dish.
//     * The dish must exist.
//     */
//    void deleteOrder(Order order);
//
//    /**
//     * Adds the given person.
//     * {@code person} must not already exist in the address book.
//     */
//    void addOrder(Order order);
//
//    /**
//     * Replaces the given person {@code target} with {@code editedPerson}.
//     * {@code target} must exist in the address book.
//     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
//     */
//    void setOrder(Order target, Order editedOrder);
//
//    /** Returns an unmodifiable view of the filtered person list */
//    ObservableList<Order> getFilteredOrderList();
}
