package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Customer> getCustomerList();

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();

    /**
     * Returns an unmodifiable view of the cheeses list.
     * This list will not contain any duplicate cheeses.
     */
    ObservableList<Cheese> getCheeseList();

}
