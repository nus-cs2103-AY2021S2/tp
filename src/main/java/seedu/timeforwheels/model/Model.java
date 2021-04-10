package seedu.timeforwheels.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.timeforwheels.commons.core.GuiSettings;
import seedu.timeforwheels.model.customer.Customer;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;

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
     * Returns the user prefs' delivery list file path.
     */
    Path getDeliveryListFilePath();

    /**
     * Sets the user prefs' delivery list file path.
     */
    void setDeliveryListFilePath(Path deliveryListFilePath);

    /**
     * Replaces delivery list data with the data in {@code deliveryList}.
     */
    void setDeliveryList(ReadOnlyDeliveryList deliveryList);

    /** Returns the DeliveryList */
    ReadOnlyDeliveryList getDeliveryList();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the delivery list.
     */
    boolean hasCustomer(Customer customer);

    /**
     * Deletes the given customer.
     * The customer must exist in the delivery list.
     */
    void deleteCustomer(Customer target);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the delivery list.
     */
    void addCustomer(Customer customer);

    /**
     * Replaces the given customer {@code target} with {@code editedCustomer}.
     * {@code target} must exist in the delivery list.
     * The customer identity of {@code editedCustomer} must not be the same as
     * another existing customer in the delivery list.
     */
    void setCustomer(Customer target, Customer editedCustomer);

    /** Returns an unmodifiable view of the filtered customer list */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Customer> predicate);
}
