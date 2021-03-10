package seedu.address.logic;

import seedu.address.model.food.FoodIntake;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Compares and sorts FoodIntake objects by the date
 */
public class foodIntakeComparator implements Comparator<FoodIntake> {

    @Override
    public int compare(FoodIntake firstIntake, FoodIntake secondIntake) {
        LocalDate firstIntakeDate = firstIntake.getDate();
        LocalDate secondIntakeDate = secondIntake.getDate();
        return firstIntakeDate.compareTo(secondIntakeDate);
    }

}
