package seedu.cakecollate.model;

import javafx.collections.ObservableList;
import seedu.cakecollate.model.order.Order;

/**
 * Unmodifiable view of an cakecollate
 */
public interface ReadOnlyCakeCollate {

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();

}
