package seedu.address.model.food.exceptions;

public class FoodIntakeNotFoundException extends RuntimeException {
    public FoodIntakeNotFoundException() {
        super("Food Intake stated does not exist in the list.");
    }
}
