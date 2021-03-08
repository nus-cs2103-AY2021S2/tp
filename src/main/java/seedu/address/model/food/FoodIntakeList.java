package seedu.address.model.food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of FoodIntakes starting from the specified date.
 */
public class FoodIntakeList {
    private LocalDateTime startDate;
    private List<FoodIntake> foodIntakeList = new ArrayList<>();

    /**
     * Constructs a FoodIntakeList.
     * @param startDate the date that the FoodIntakeList begins collecting from
     */
    public FoodIntakeList(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Adds a FoodIntake object to the FoodIntakeList.
     * @param foodIntake FoodIntake object to add to list
     */
    public void addFoodIntake(FoodIntake foodIntake) {
        this.foodIntakeList.add(foodIntake);
    }

    public List<FoodIntake> getList() {
        return this.foodIntakeList;
    }
}
