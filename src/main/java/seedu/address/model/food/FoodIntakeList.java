package seedu.address.model.food;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.DailyFoodIntakeCalculator;
import seedu.address.logic.FoodIntakeComparator;
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

        //Set to collect total nutrients value and pass to food intake calculator to calculate.
        Double carbos = 0.0;
        Double fats = 0.0;
        Double proteins = 0.0;
        boolean hasItem = false;
        DailyFoodIntakeCalculator foodIntakeCalculator;

        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if (foodIntakeList.get(i).getDate().isEqual(date)) {
                hasItem = true;
                Food tempFood = foodIntakeList.get(i).getFood();
                carbos += tempFood.getCarbos();
                fats += tempFood.getFats();
                proteins += tempFood.getProteins();
                stringBuilder.append(foodIntakeList.get(i) + "\n");
            } else if (foodIntakeList.get(i).getDate().isAfter(date)) {
                break;
            }
        }
        foodIntakeCalculator = new DailyFoodIntakeCalculator(carbos, fats, proteins);
        if (hasItem) {
            stringBuilder.append("Total Daily Calories Intake: " + foodIntakeCalculator.getCalories() + " calories.\n");
        } else {
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
        stringBuilder.append("Summary Food Intake from ("
                + from.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ") to ("
                + to.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "):\n");
        return processFoodIntakeListByDate(stringBuilder, from, to);
    }

    /**
     * Process food intake items data one by one based on the date and aggregate output together with its date.
     *
     * @param stringBuilder string builder object
     * @param from          starting date
     * @param to            end date
     * @return string output of all food intake items by its date
     */
    public String processFoodIntakeListByDate(StringBuilder stringBuilder, LocalDate from, LocalDate to) {
        Double carbos = 0.0;
        Double fats = 0.0;
        Double proteins = 0.0;
        DailyFoodIntakeCalculator foodIntakeCalculator;
        LocalDate tempDate = null; //Set for loop reference
        boolean isFirst = true;

        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            if ((foodIntakeList.get(i).getDate().isEqual(from) || foodIntakeList.get(i).getDate().isEqual(to))
                    || (foodIntakeList.get(i).getDate().isAfter(from)
                    && foodIntakeList.get(i).getDate().isBefore(to))) {
                if (isFirst) {
                    isFirst = false;
                    stringBuilder.append("Summary Food Intake for the Day ("
                            + foodIntakeList.get(i).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                            + "):\n");
                }
                Food tempFood = foodIntakeList.get(i).getFood();
                carbos += tempFood.getCarbos();
                fats += tempFood.getFats();
                proteins += tempFood.getProteins();
                tempDate = foodIntakeList.get(i).getDate();
                stringBuilder.append(foodIntakeList.get(i) + "\n");

                //If it reaches end of list or end of current date
                if (i + 1 == this.foodIntakeList.size()
                        || !tempDate.isEqual(this.foodIntakeList.get(i + 1).getDate())) {
                    foodIntakeCalculator = new DailyFoodIntakeCalculator(carbos, fats, proteins);
                    stringBuilder.append("Total Daily Calories Intake: "
                            + foodIntakeCalculator.getCalories() + " calories.\n");
                    if (i + 1 != this.foodIntakeList.size() &&
                            !tempDate.isEqual(this.foodIntakeList.get(i + 1).getDate())) {
                        stringBuilder.append("Summary Food Intake for the Day ("
                                + foodIntakeList.get(i + 1).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                                + "):\n");
                    }
                    carbos = 0.0;
                    fats = 0.0;
                    proteins = 0.0;
                }
            } else if (foodIntakeList.get(i).getDate().isAfter(to)) {
                break;
            }
        }
        if (isFirst) {
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
