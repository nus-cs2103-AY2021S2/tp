package chim.model.order.exceptions;

/**
 * Signals that the operation will result in duplicate orders (Orders are considered duplicates if they have the same
 * identity).
 */
public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException() {
        super("Operation would result in duplicate orders");
    }
}
