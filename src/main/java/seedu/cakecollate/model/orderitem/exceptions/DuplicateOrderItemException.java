package seedu.cakecollate.model.orderitem.exceptions;

/**
 * Signals that the operation will result in duplicate Order Items
 * (Order Items are considered duplicates if they have the same type).
 */
public class DuplicateOrderItemException extends RuntimeException {
    public DuplicateOrderItemException() {
        super("Operation would result in duplicate order items.");
    }
}
