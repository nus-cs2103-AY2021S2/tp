package seedu.address.model.food;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a FoodIntake record.
 */
public class FoodIntake {
    private static final String DATETIME_FORMAT = "d MMM yyyy h:mma";
    private LocalDateTime date;
    private Food food;

    /**
     * Creates a FoodIntake object representing the Food consumed at a particular date and time.
     *
     * @param date LocalDateTime of when the food was eaten
     * @param food The related Food object that was consumed
     */
    public FoodIntake(LocalDateTime date, Food food) {
        this.date = date;
        this.food = food;
    }

    public Food getFood() {
        return this.food;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)) + ": "
                + this.food.toString();
    }
}
