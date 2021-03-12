package seedu.address.model.food.exceptions;

public class DuplicateFoodItemException extends RuntimeException {
    public DuplicateFoodItemException() {
        super("Operation would result in duplicate food items.");
    }
}
