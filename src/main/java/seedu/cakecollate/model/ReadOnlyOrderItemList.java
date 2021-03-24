package seedu.cakecollate.model;

import javafx.collections.ObservableList;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Unmodifiable view Order Items
 */

public interface ReadOnlyOrderItemList {
    /**
     * Returns an unmodifiable view of the order items list.
     * This list will not contain any duplicate order items.
     */
    ObservableList<OrderItem> getOrderItemList();
}
