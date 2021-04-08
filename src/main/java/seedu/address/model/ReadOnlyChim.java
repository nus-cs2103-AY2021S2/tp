package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;

/**
 * Unmodifiable view of CHIM's customers, orders and cheeses lists.
 */
public interface ReadOnlyChim {

    /**
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate customers.
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
