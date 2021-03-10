package seedu.address.logic;

import java.time.LocalDate;
import java.util.Comparator;

import seedu.address.model.food.FoodIntake;

/**
 * Compares and sorts FoodIntake objects by the date
 */
public class FoodIntakeComparator implements Comparator<FoodIntake> {

    @Override
    public int compare(FoodIntake firstIntake, FoodIntake secondIntake) {
        LocalDate firstIntakeDate = firstIntake.getDate();
        LocalDate secondIntakeDate = secondIntake.getDate();
        return firstIntakeDate.compareTo(secondIntakeDate);
    }

}
