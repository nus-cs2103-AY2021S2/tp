package seedu.address.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;

import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.MacroNutrientComposition;
import seedu.address.model.food.Food;
import seedu.address.model.food.FoodIntake;
import seedu.address.model.food.FoodIntakeList;

/**
 * Class for generating a progress report of diet plan.
 */
public class ProgressCalculator {

    // Leeway value for adherence to diet requirements (in percentage)
    public static final double LEEWAY = 5.00;

    /**
     * Calculates and reports on how much percentage of each day's food intake is adhering to the diet plan.
     *
     * @param dietPlan The diet plan to calculate daily food intake against.
     * @param foodIntakeList The food consumption for each day.
     * @return Progress Report
     */
    public static String calculateProgress(FoodIntakeList foodIntakeList, DietPlan dietPlan) {

        // Get list of Foods
        List<FoodIntake> foodIntakes = foodIntakeList.getList();

        // Sort food intake list by dates
        Collections.sort(foodIntakes, new FoodIntakeComparator());

        // Get the daily requirements of the diet plan
        MacroNutrientComposition composition = dietPlan.getMacroNutrientComposition();
        double dailyFats = composition.getFats();
        double dailyCarbs = composition.getCarbohydrates();
        double dailyProteins = composition.getProteins();

        // Print details of diet plan
        StringBuilder report = new StringBuilder();
        report.append(dietPlan.viewPlan());

        // Dates that progress report is listing
        report.append("Here is the report for the days ");
        LocalDate firstIntakeDay = foodIntakes.get(0).getDate();
        LocalDate lastIntakeDay = foodIntakes.get(foodIntakes.size() - 1).getDate();
        report.append(firstIntakeDay.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        report.append(" to ");
        report.append(lastIntakeDay.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        report.append(":\n\n");

        // For each day, give a progress report on whether intake is over or less
        // than the required amount.
        LocalDate previousDay = null;
        double carbsSum = 0.0;
        double fatsSum = 0.0;
        double proteinsSum = 0.0;
        int counter = 0;
        for (FoodIntake foodIntake : foodIntakes) {
            // Report on date
            LocalDate day = foodIntake.getDate();

            if (!day.equals(previousDay)) {
                // New Day

                if (counter != 0) {
                    // Calculate total adherence percentages
                    double carbsAdherence = calculatePercentage(carbsSum, dailyCarbs * counter);
                    double fatsAdherence = calculatePercentage(fatsSum, dailyFats * counter);
                    double proteinsAdherence = calculatePercentage(proteinsSum, dailyProteins * counter);

                    // Report daily adherence percentage
                    if (carbsAdherence > 100 + LEEWAY) {
                        double exceed = carbsAdherence - (100.00 - LEEWAY);
                        String exceedString = String.format("%,.2f", exceed);
                        report.append("Your daily carbohydrate consumption has exceeded by " + exceedString + "%\n\n");
                    } else if (carbsAdherence < 100 - LEEWAY) {
                        double under = (100 - LEEWAY) - carbsAdherence;
                        String underString = String.format("%,.2f", under);
                        report.append("Your daily carbohydrate consumption is under by " + underString + "%\n\n");
                    } else {
                        report.append("Your daily carbohydrate consumption "
                                + "is within diet requirements. Well done!\n\n");
                    }

                    if (fatsAdherence > 100 + LEEWAY) {
                        double exceed = fatsAdherence - (100.00 - LEEWAY);
                        String exceedString = String.format("%,.2f", exceed);
                        report.append("Your daily fat consumption has exceeded by " + exceedString + "%\n\n");
                    } else if (fatsAdherence < 100 - LEEWAY) {
                        double under = (100 - LEEWAY) - fatsAdherence;
                        String underString = String.format("%,.2f", under);
                        report.append("Your daily fat consumption is under by " + underString + "%\n\n");
                    } else {
                        report.append("Your daily fat consumption is within diet requirements. Well done!\n\n");
                    }

                    if (proteinsAdherence > 100 + LEEWAY) {
                        double exceed = proteinsAdherence - (100.00 - LEEWAY);
                        String exceedString = String.format("%,.2f", exceed);
                        report.append("Your daily protein consumption has exceeded by " + exceedString + "%\n\n");
                    } else if (proteinsAdherence < 100 - LEEWAY) {
                        double under = (100 - LEEWAY) - proteinsAdherence;
                        String underString = String.format("%,.2f", under);
                        report.append("Your daily protein consumption is under by " + underString + "%\n\n");
                    } else {
                        report.append("Your daily protein consumption is within diet requirements. Well done!\n\n");
                    }

                }

                // Reset counter and sums
                counter = 1;
                carbsSum = 0.0;
                fatsSum = 0.0;
                proteinsSum = 0.0;

                // Report new day
                report.append("Date: ");
                report.append(day.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
                report.append("\n");
                previousDay = day;

                report.append("  Name\tCarbohydrates\tFats\tProtein\n");
            }

            // Report on foods consumed and macronutrients
            Food food = foodIntake.getFood();

            // Get macronutrients consumed
            double carbs = food.getCarbos();
            double fats = food.getFats();
            double proteins = food.getProteins();

            // Format consumed macronutrients
            String carbsString = String.format("%,.2f", carbs);
            String fatsString = String.format("%,.2f", fats);
            String proteinsString = String.format("%,.2f", proteins);

            report.append((counter + 1) + "  " + food.getName() + "\t");
            report.append(carbsString + "\t");
            report.append(fatsString + "\t");
            report.append(proteinsString + "\t\n");

            // Store sum of macronutrient consumption
            carbsSum += carbs;
            fatsSum += fats;
            proteinsSum += proteins;

            // Increment counter
            counter++;
        }

        // Return the final report
        return report.toString();

    }

    private static double calculatePercentage(double intake, double required) {
        return (intake / required) * 100.00;
    }

}
