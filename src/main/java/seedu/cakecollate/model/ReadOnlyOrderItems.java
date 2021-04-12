package seedu.cakecollate.model;

import javafx.collections.ObservableList;
import seedu.cakecollate.model.orderitem.OrderItem;

/**
 * Unmodifiable view of Order Items
 */

public interface ReadOnlyOrderItems {
    /**
     * Returns an unmodifiable view of the order items list.
     * This list will not contain any duplicate order items.
     */
    ObservableList<OrderItem> getOrderItemList();
}
