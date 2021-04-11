package seedu.address.testutil;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import seedu.address.model.DietLah;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.User;

/**
 * A utility class containing a DietLah object to be used in tests.
 */
public class TypicalDietLah {

    private TypicalDietLah() {} // prevents instantiation

    /**
     * Returns an {@code DietLah} with all the typical values.
     */
    public static DietLah getTypicalDietLah() {
        DietLah ab = new DietLah(getTypicalUniqueFoodList(), getTypicalFoodIntakeList(), getTypicalUser());
        return ab;
    }

    public static User getTypicalUser() {
        Bmi typicalBmi = new Bmi(61.4, 168.5);

        User typicalUser = new User();
        return typicalUser;
    }

    public static UniqueFoodList getTypicalUniqueFoodList() {
        UniqueFoodList typicalUniqueFoodList = new UniqueFoodList();

        Food apple = new Food("apple", 19.06, 0.24, 0.36);
        Food eggs = new Food("eggs", 1.15, 7.14, 6.81);

        typicalUniqueFoodList.addFoodItem(apple);
        typicalUniqueFoodList.addFoodItem(eggs);

        return typicalUniqueFoodList;
    }

    public static FoodIntakeList getTypicalFoodIntakeList() {
        FoodIntakeList typicalFoodIntakeList = new FoodIntakeList();

        UniqueFoodList typicalUniqueFoodList = getTypicalUniqueFoodList();
        ObservableList<Food> foodList = typicalUniqueFoodList.getFoodList();

        FoodIntake firstIntake = new FoodIntake(LocalDate.now(), foodList.get(0));
        FoodIntake secondIntake = new FoodIntake(LocalDate.now(), foodList.get(1));

        typicalFoodIntakeList.addFoodIntake(firstIntake);
        typicalFoodIntakeList.addFoodIntake(secondIntake);

        return typicalFoodIntakeList;
    }

}
