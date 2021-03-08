package seedu.address.model.food;

import java.time.LocalDateTime;

public class FoodIntake {
    private LocalDateTime date;
    private Food food;
    
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
}
