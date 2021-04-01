package seedu.address.logic;

import java.time.LocalDate;
import java.util.Comparator;

import seedu.address.model.food.FoodIntake;

/**
 * Compares and sorts FoodIntake objects by the date, otherwise alphabetically.
 */
public class FoodIntakeComparator implements Comparator<FoodIntake> {

    @Override
    public int compare(FoodIntake firstIntake, FoodIntake secondIntake) {
        LocalDate firstIntakeDate = firstIntake.getDate();
        LocalDate secondIntakeDate = secondIntake.getDate();
        if (firstIntakeDate.isEqual(secondIntakeDate)) { //If both dates are the same, sort alphabetically.
            return firstIntake.getFood().getName().compareTo(secondIntake.getFood().getName());
        } else {
            return firstIntakeDate.compareTo(secondIntakeDate);
        }
    }

}
