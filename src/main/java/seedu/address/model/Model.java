package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.model.order.Quantity;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;
    Predicate<Cheese> PREDICATE_SHOW_ALL_CHEESES = unused -> true;

    Comparator<Customer> COMPARATOR_NORMAL_CUSTOMER = Comparator.comparing(x -> x.getId().value);
    Comparator<Order> COMPARATOR_NORMAL_ORDER = Comparator.comparing(x -> x.getOrderId().value);
    Comparator<Cheese> COMPARATOR_NORMAL_CHEESE = Comparator.comparing(x -> x.getCheeseId().value);

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

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    boolean hasCustomer(Customer customer);

    /**
     * Returns true if a customer with the {@code phone} exists in the address book.
     */
    boolean hasCustomerWithPhone(Phone phone);

    /**
     * Returns a customer with the {@code phone} if exists in the address book.
     */
    Customer getCustomerWithPhone(Phone phone);

    /**
     * Deletes the given customer.
     * The customer must exist in the address book.
     */
    void deleteCustomer(Customer target);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the address book.
     */
    void addCustomer(Customer customer);

    /**
     * Replaces the given customer {@code target} with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in
     * the address book.
     */
    void setCustomer(Customer target, Customer editedCustomer);

    /**
     * Returns true if a Order with the same identity as {@code order} exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     */
    void deleteOrder(Order target);

    /**
     * Adds the given order.
     * {@code order} must not already exist in the address book.
     */
    void addOrder(Order order);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in
     * the address book.
     */
    void setOrder(Order target, Order editedOrder);

    /**
     * Returns true if a Cheese with the same identity as {@code cheese} exists in the address book.
     */
    boolean hasCheese(Cheese cheese);

    /**
     * Deletes the given cheese.
     * The cheese must exist in the address book.
     */
    void deleteCheese(Cheese target);

    /**
     * Adds the given cheese.
     * {@code cheese} must not already exist in the address book.
     */
    void addCheese(Cheese cheese);

    /**
     * Replaces the given cheese {@code target} with {@code editedCheese}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedCheese} must not be the same as another existing order in
     * the address book.
     */
    void setCheese(Cheese target, Cheese editedcheese);

    /**
     * Return Unassigned cheeses with given cheeseType.
     */
    Set<CheeseId> getUnassignedCheeses(CheeseType cheesetype, Quantity quantity);

    /**
     * Updates cheeses with cheeseId found in cheesesAssigned to be assigned
     * @param cheesesAssigned , set of cheese Ids
     */
    void updateCheesesStatus(Set<CheeseId> cheesesAssigned);

    /**
     * Returns an unmodifiable view of the filtered customer list
     */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Returns an unmodifiable view of the filtered cheese list
     */
    ObservableList<Cheese> getFilteredCheeseList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Customer> predicate);

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Updates the filter of the filtered cheese list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCheeseList(Predicate<Cheese> predicate);

    /**
     * Sets the list panel in the UI to show the filtered customers list.
     */
    void setPanelToCustomerList();

    /**
     * Sets the list panel in the UI to show the filtered cheeses list.
     */
    void setPanelToCheeseList();

    /**
     * Sets the list panel in the UI to show the filtered orders list.
     */
    void setPanelToOrderList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedCustomerList(Comparator<Customer> comparator);

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedOrderList(Comparator<Order> comparator);

    /**
     * Updates the filter of the filtered cheese list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedCheeseList(Comparator<Cheese> comparator);
}
