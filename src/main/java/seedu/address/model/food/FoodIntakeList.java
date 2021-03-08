package seedu.address.model.food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FoodIntakeList {
    private LocalDateTime startDate;
    private List<FoodIntake> foodIntakeList = new ArrayList<>();

    public FoodIntakeList(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void addFoodIntake(FoodIntake foodIntake) {
        this.foodIntakeList.add(foodIntake);
    }

    public List<FoodIntake> getList() {
        return this.foodIntakeList;
    }
}
