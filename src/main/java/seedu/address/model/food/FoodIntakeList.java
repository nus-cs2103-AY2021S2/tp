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
     * Constructs a FoodIntakeList without date.
     */
    public FoodIntakeList() {
        this.startDate = null;
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
     * Removes a FoodIntake item by index from the FoodIntakeList.
     *
     * @param index index of food intake item
     */
    public void deleteFoodIntake(int index) throws FoodIntakeNotFoundException {
        this.foodIntakeList.remove(index);
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
     * @return all FoodIntake object that are created for that date in String output
     */
    public String getFoodIntakeListByDate(LocalDate date) {
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(this.foodIntakeList, new FoodIntakeComparator());
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(date)) {
                stringBuilder.append(foodIntakeList.get(i) + "\n");
            } else if (foodIntakeList.get(i).getDate().isAfter(date)) {
                break;
            }
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("No record found during this date.");
        }
        return stringBuilder.toString();
    }

    /**
     * Gets all FoodIntake object based on the date range provided inclusive in String output.
     *
     * @param from start date
     * @param to   end date
     * @return all FoodIntake object that lies within the date range in String output
     */
    public String getFoodIntakeListByDateRange(LocalDate from, LocalDate to) {
        Collections.sort(this.foodIntakeList, new FoodIntakeComparator());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(from) || foodIntakeList.get(i).getDate().isEqual(to)) {
                stringBuilder.append(foodIntakeList.get(i) + "\n");
            } else if (foodIntakeList.get(i).getDate().isAfter(from) && foodIntakeList.get(i).getDate().isBefore(to)) {
                stringBuilder.append(foodIntakeList.get(i) + "\n");
            } else if (foodIntakeList.get(i).getDate().isAfter(to)) {
                break;
            }
        }
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append("No record found during this period of time.");
        }
        return stringBuilder.toString();
    }
}
