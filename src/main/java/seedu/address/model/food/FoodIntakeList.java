package seedu.address.model.food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of FoodIntakes starting from the specified date.
 */
public class FoodIntakeList {
    private LocalDate startDate;
    private List<FoodIntake> foodIntakeList = new ArrayList<>();

    /**
     * Constructs a FoodIntakeList.
     * @param startDate the date that the FoodIntakeList begins collecting from
     */
    public FoodIntakeList(LocalDate startDate) {
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
