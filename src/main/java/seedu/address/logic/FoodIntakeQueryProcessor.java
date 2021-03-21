package seedu.address.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;

/**
 * Represents a class to generate daily food intake report.
 */
public class FoodIntakeQueryProcessor {
    private static final String DATE_FORMAT = "d MMM yyyy";
    private ObservableList<FoodIntake> foodIntakeList;

    public FoodIntakeQueryProcessor(ObservableList<FoodIntake> foodIntakeList) {
        this.foodIntakeList = foodIntakeList;
    }

    /**
     * Generates a single day report on the calories consumption.
     *
     * @return string output of a single day consumption
     */
    public String generateDayQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        Double carbos = 0.0;
        Double fats = 0.0;
        Double proteins = 0.0;
        FoodIntakeCalculator foodIntakeCalculator;
        int counter = 1;
        for (int i = 0; i < this.foodIntakeList.size(); i++) {
            Food tempFood = foodIntakeList.get(i).getFood();
            carbos += tempFood.getCarbos();
            fats += tempFood.getFats();
            proteins += tempFood.getProteins();
            stringBuilder.append(counter + ". " + foodIntakeList.get(i) + "\n");
            counter++;
        }
        foodIntakeCalculator = new FoodIntakeCalculator(carbos, fats, proteins);
        stringBuilder.append("Total Daily Calories Intake: " + foodIntakeCalculator.getCalories() + " calories.\n");
        return stringBuilder.toString();
    }

    /**
     * Generates a range of day report on their individual daily calories consumption.
     *
     * @return string output of a range of days consumption
     */
    public String generateRangeOfDaysQuery() {
        Double carbos = 0.0;
        Double fats = 0.0;
        Double proteins = 0.0;
        int counter = 1;
        LocalDate currDate = null;
        FoodIntakeCalculator foodIntakeCalculator;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < this.foodIntakeList.size(); i++) {
            if(!this.foodIntakeList.get(i).getDate().equals(currDate)) {
                currDate = this.foodIntakeList.get(i).getDate(); //Set new date when a new data date is different
                stringBuilder.append("Summary Food Intake for the Day ("
                        + foodIntakeList.get(i).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                        + "):\n");
                carbos = 0.0;
                fats = 0.0;
                proteins = 0.0;
            }
            Food tempFood = foodIntakeList.get(i).getFood();
            carbos += tempFood.getCarbos();
            fats += tempFood.getFats();
            proteins += tempFood.getProteins();
            stringBuilder.append(counter + ". " + foodIntakeList.get(i) + "\n");
            counter++;
            if(i + 1 == this.foodIntakeList.size()
                    || !currDate.isEqual(this.foodIntakeList.get(i + 1).getDate())) {
                foodIntakeCalculator = new FoodIntakeCalculator(carbos, fats, proteins);
                stringBuilder.append("Total Daily Calories Intake: "
                        + foodIntakeCalculator.getCalories() + " calories.\n");
                counter = 1;
            }
        }
        return stringBuilder.toString();
    }
}
