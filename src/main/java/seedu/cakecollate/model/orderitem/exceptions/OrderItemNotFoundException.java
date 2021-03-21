package seedu.cakecollate.model.orderitem.exceptions;

/**
 * Signals that the operation is unable to find the specified order item.
 */

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException() {
        super("Order Item was not found in the UniqueOrderItem list.");
    }
}


