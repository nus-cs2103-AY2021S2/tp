package seedu.timeforwheels.model;

import javafx.collections.ObservableList;
import seedu.timeforwheels.model.customer.Customer;

/**
 * Unmodifiable view of an delivery list
 */
public interface ReadOnlyDeliveryList {

    /**
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate customers.
     */
    ObservableList<Customer> getCustomerList();

}
