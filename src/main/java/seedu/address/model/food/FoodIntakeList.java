package seedu.address.model.food;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.FoodIntakeComparator;
import seedu.address.logic.FoodIntakeQueryProcessor;
import seedu.address.model.food.exceptions.FoodIntakeNotFoundException;

/**
 * Represents a list of FoodIntakes starting from the specified date.
 */
public class FoodIntakeList {
    private static final String DATE_FORMAT = "d MMM yyyy";
    private ObservableList<FoodIntake> foodIntakeList;

    /**
     * Constructs a FoodIntakeList.
     */
    public FoodIntakeList() {
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
     * Updates the FoodIntake object in the FoodIntakeList with another FoodIntake
     *
     * @param index      index to replace
     * @param foodIntake FoodIntake object to replace
     */
    public void updateFoodIntake(int index, FoodIntake foodIntake) throws FoodIntakeNotFoundException {
        this.foodIntakeList.set(index, foodIntake);
    }

    /**
     * Gets the first index of a matching food intake item by date and name
     *
     * @return index of FoodIntake
     */
    public int findFoodIntake(LocalDate date, String name) {
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(date)
                    && foodIntakeList.get(i).getFood().getName().equals(name)) {
                return i;
            }
        }
        return -1;
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
     * Gets all FoodIntake object from the FoodIntakeList and outputs them in String format. (DELETE IF UNUSED)
     *
     * @return all FoodIntake items that are created in String output
     */
    public String getAllFoodIntakeList() {
        StringBuilder stringBuilder = new StringBuilder();
        ObservableList<FoodIntake> sortedFoodIntakeList = FXCollections.observableArrayList();
        sortedFoodIntakeList = getFoodIntakeList();
        FoodIntakeQueryProcessor foodIntakeQueryProcessor = new FoodIntakeQueryProcessor(sortedFoodIntakeList);

        LocalDate startDate;
        LocalDate endDate;
        if (sortedFoodIntakeList.size() > 0) {
            startDate = sortedFoodIntakeList.get(0).getDate();
            endDate = sortedFoodIntakeList.get(sortedFoodIntakeList.size() - 1).getDate();
            if (sortedFoodIntakeList.size() == 1) {
                stringBuilder.append("Summary Food Intake for the Day ("
                        + startDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "):\n");
                stringBuilder.append(foodIntakeQueryProcessor.generateDayQuery());
            } else {
                stringBuilder.append("Summary Food Intake from ("
                        + startDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ") to ("
                        + endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "):\n");
                stringBuilder.append(foodIntakeQueryProcessor.generateRangeOfDaysQuery());
            }
        } else {
            stringBuilder.append("No record found during this date.");
        }
        return stringBuilder.toString();
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
        stringBuilder.append("Summary Food Intake for the Day ("
                + date.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "):\n");

        ObservableList<FoodIntake> filterFoodIntakeList = FXCollections.observableArrayList();
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(date)) {
                filterFoodIntakeList.add(foodIntakeList.get(i));
            } else if (foodIntakeList.get(i).getDate().isAfter(date)) {
                break;
            }
        }
        if (filterFoodIntakeList.size() > 0) {
            FoodIntakeQueryProcessor foodIntakeQueryProcessor = new FoodIntakeQueryProcessor(filterFoodIntakeList);
            stringBuilder.append(foodIntakeQueryProcessor.generateDayQuery());
        } else {
            stringBuilder.append("No record found during this date.");
        }
        return stringBuilder.toString();
    }

    /**
     * Gets all FoodIntake object based on the date range and process them.
     *
     * @param from start date
     * @param to   end date
     * @return all FoodIntake object that lies within the date range in String output
     */
    public String getFoodIntakeListByDateRange(LocalDate from, LocalDate to) {
        Collections.sort(this.foodIntakeList, new FoodIntakeComparator());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Summary Food Intake from ("
                + from.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ") to ("
                + to.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "):\n");

        ObservableList<FoodIntake> filterFoodIntakeList = FXCollections.observableArrayList();

        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if ((foodIntakeList.get(i).getDate().isEqual(from) || foodIntakeList.get(i).getDate().isEqual(to))
                    || (foodIntakeList.get(i).getDate().isAfter(from)
                    && foodIntakeList.get(i).getDate().isBefore(to))) {
                filterFoodIntakeList.add(foodIntakeList.get(i));
            }
        }

        FoodIntakeQueryProcessor foodIntakeQueryProcessor = new FoodIntakeQueryProcessor(filterFoodIntakeList);
        if (filterFoodIntakeList.size() > 0) {
            stringBuilder.append(foodIntakeQueryProcessor.generateRangeOfDaysQuery());
        } else {
            stringBuilder.append("No record found during this period.");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FoodIntakeList other = (FoodIntakeList) o;
        return Objects.equals(foodIntakeList, other.foodIntakeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodIntakeList);
    }
}
