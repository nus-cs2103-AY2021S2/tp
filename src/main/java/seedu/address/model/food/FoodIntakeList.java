package seedu.address.model.food;

import java.time.LocalDate;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.FoodIntakeComparator;
import seedu.address.model.food.exceptions.FoodIntakeNotFoundException;

/**
 * Represents a list of FoodIntakes starting from the specified date.
 */
public class FoodIntakeList {
    private LocalDate startDate;
    private ObservableList<FoodIntake> foodIntakeList;

    /**
     * Constructs a FoodIntakeList.
     *
     * @param startDate the date that the FoodIntakeList begins collecting from
     */
    public FoodIntakeList(LocalDate startDate) {
        this.startDate = startDate;
        this.foodIntakeList = FXCollections.observableArrayList();
    }

    /**
     * Adds a FoodIntake object to the FoodIntakeList.
     *
     * @param foodIntake FoodIntake object to add to list
     */
    public void addFoodIntake(FoodIntake foodIntake) {
        this.foodIntakeList.add(foodIntake);
    }

    /**
     * Removes a FoodIntake object from the FoodIntakeList.
     *
     * @param localDate date of the possible food intake item
     * @param foodName  name of the possible food intake item
     */
    public void deleteFoodIntake(LocalDate localDate, String foodName) throws FoodIntakeNotFoundException {
        for (int i = 0; i < foodIntakeList.size(); i++) {
            FoodIntake temp = foodIntakeList.get(i);
            if (temp.getFood().getName().equals(foodName)
                    && temp.getDate().isEqual(localDate)) {
                this.foodIntakeList.remove(i);
                return;
            }
        }
        throw new FoodIntakeNotFoundException();
    }

    /**
     * Gets all FoodIntake object from the FoodIntakeList.
     *
     * @return all FoodIntake object in the list
     */
    public ObservableList<FoodIntake> getFoodIntakeList() {
        Collections.sort(this.foodIntakeList, new FoodIntakeComparator());
        return this.foodIntakeList;
    }

    /**
     * Gets all FoodIntake object based on the date provided.
     *
     * @param date filter date
     * @return all FoodIntake object that are created for that date
     */
    public ObservableList<FoodIntake> getFoodIntakeListByDate(LocalDate date) {
        Collections.sort(this.foodIntakeList, new FoodIntakeComparator());
        ObservableList<FoodIntake> tempList = FXCollections.observableArrayList();
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(date)) {
                tempList.add(foodIntakeList.get(i));
            } else if (foodIntakeList.get(i).getDate().isAfter(date)) {
                break;
            }
        }
        return tempList;
    }

    /**
     * Gets all FoodIntake object based on the date range provided inclusive.
     *
     * @param from start date
     * @param to   end date
     * @return all FoodIntake object that lies within the date range
     */
    public ObservableList<FoodIntake> getFoodIntakeListByDateRange(LocalDate from, LocalDate to) {
        Collections.sort(this.foodIntakeList, new FoodIntakeComparator());
        ObservableList<FoodIntake> tempList = FXCollections.observableArrayList();
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(from) || foodIntakeList.get(i).getDate().isEqual(to)) {
                tempList.add(foodIntakeList.get(i));
            } else if (foodIntakeList.get(i).getDate().isAfter(from) && foodIntakeList.get(i).getDate().isBefore(to)) {
                tempList.add(foodIntakeList.get(i));
            } else if (foodIntakeList.get(i).getDate().isAfter(to)) {
                break;
            }
        }
        return tempList;
    }
}
