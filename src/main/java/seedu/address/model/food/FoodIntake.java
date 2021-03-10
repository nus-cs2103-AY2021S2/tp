package seedu.address.model.food;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a FoodIntake record.
 */
public class FoodIntake {
    private static final String DATE_FORMAT = "d MMM yyyy";
    private LocalDate date;
    private Food food;

    /**
     * Creates a FoodIntake object representing the Food consumed at a particular date and time.
     *
     * @param date LocalDateTime of when the food was eaten
     * @param food The related Food object that was consumed
     */
    public FoodIntake(LocalDate date, Food food) {
        this.date = date;
        this.food = food;
    }

    public Food getFood() {
        return this.food;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ": "
                + this.food.toString();
    }
}
