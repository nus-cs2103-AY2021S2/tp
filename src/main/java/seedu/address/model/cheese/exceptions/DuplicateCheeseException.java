package seedu.address.model.cheese.exceptions;

/**
 * Signals that the operation will result in duplicate Cheeses (Cheese are considered duplicates if they have the same
 * identity).
 */
public class DuplicateCheeseException extends RuntimeException {
    public DuplicateCheeseException() {
        super("Operation would result in duplicate cheese");
    }
}
