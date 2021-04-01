package seedu.address.model.food.exceptions;

public class FoodItemNotFoundException extends RuntimeException {
    public FoodItemNotFoundException() {
        super("Food item does not exist in the list.");
    }
}
