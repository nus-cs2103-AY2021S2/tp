package seedu.address.logic;

import seedu.address.model.diet.DietPlan;
import seedu.address.model.diet.MacroNutrientComposition;
import seedu.address.model.diet.PlanType;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.User;

/**
 * Class for calculating diet plan information specific to user
 */
public class PlanInfoCalculator {

    // Required calories for weight gain and weight loss
    private static final double WEIGHTGAINCALORIES = 400.00;
    private static final double WEIGHTLOSSCALORIES = 500.00;

    private double calories;
    private double carbohydrates;
    private double proteins;
    private double fats;

    /**
     * Constructor for PlanInfoCalculator class.
     * Calculates the amount of calories specific to the user's information and diet plan.
     *
     * @param user The user object.
     * @param dietPlan The diet plan to calculate against.
     */
    public PlanInfoCalculator(User user, DietPlan dietPlan) {
        // Calculate calories needed for diet plan
        PlanType planType = dietPlan.getPlanType();
        this.calories = calculateCalories(user, planType);

        // Calculate macronutrients needed for diet plan
        MacroNutrientComposition macroComposition = dietPlan.getMacroNutrientComposition();
        calculateMacros(macroComposition, this.calories);
    }

    /**
     * Getter for calories to be consumed for plan
     *
     * @return Calorie intake to adhere to
     */
    public double getCalories() {
        return this.calories;
    }

    /**
     * Getter for carbohydrates to be consumed for plan
     *
     * @return Carbohydrate intake to adhere to
     */
    public double getCarbohydrates() {
        return this.carbohydrates;
    }

    /**
     * Getter for fats to be consumed for plan
     *
     * @return Fat intake to adhere to
     */
    public double getFats() {
        return this.fats;
    }

    /**
     * Getter for proteins to be consumed for plan
     *
     * @return Protein intake to adhere to
     */
    public double getProteins() {
        return this.proteins;
    }

    /**
     * Updates macronutrients class variables according to its percentage in diet plan
     * and the total amount of calories
     *
     * @param macroNutrientComposition Macronutrient percentages
     * @param calories Calories to calculate percentages against
     */
    private void calculateMacros(MacroNutrientComposition macroNutrientComposition,
                                 double calories) {
        double carboPercent = macroNutrientComposition.getCarbohydrates();
        double fatPercent = macroNutrientComposition.getFats();
        double proteinPercent = macroNutrientComposition.getProteins();

        double carboCalories = (carboPercent / 100.00) * calories;
        this.carbohydrates = carboCalories / 4.0;

        double fatCalories = (fatPercent / 100.00) * calories;
        this.fats = fatCalories / 9.0;

        double proteinCalories = (proteinPercent / 100.00) * calories;
        this.proteins = proteinCalories / 4.0;
    }

    /**
     * Calculates the amount of calories needed based on diet plan type
     *
     * @param user User information
     * @param planType Type of diet plan
     * @return Double value containing appropriate amount of calories
     */
    private double calculateCalories(User user, PlanType planType) {
        Age userAge = user.getAge();
        int age = userAge.getAge();

        Bmi bmi = user.getBmi();
        double height = bmi.getHeight();
        double weight = bmi.getWeight();

        double maintenanceCalories = calculateMaintenance(user);

        switch (planType) {
        case WEIGHTGAIN:
            return calculateWeightGain(maintenanceCalories);
        case WEIGHTLOSS:
            return calculateWeightLoss(maintenanceCalories);
        default:
            return maintenanceCalories;
        }

    }

    /**
     * Calculates the amount of calories needed for weight maintenance
     *
     * @param user User information
     * @return Double value containing appropriate amount of calories
     */
    private double calculateMaintenance(User user) {
        Age userAge = user.getAge();
        int age = userAge.getAge();

        Bmi bmi = user.getBmi();
        double height = bmi.getHeight();
        double weight = bmi.getWeight();

        Gender userGender = user.getGender();
        String gender = userGender.getGender();

        double maintenanceCalories = 0;
        if (gender.equals("M")) {
            maintenanceCalories = (10.0 * weight) + (6.25 * height) - (5.0 * age) + 5.0;
        } else {
            maintenanceCalories = (10.0 * weight) + (6.25 * height) - (5.0 * age) - 161.0;
        }

        return maintenanceCalories;
    }

    /**
     * Calculates the amount of calories needed for weight gain
     *
     * @param maintenanceCalories Calories needed for maintenance
     * @return Double value containing appropriate amount of calories
     */
    private double calculateWeightGain(double maintenanceCalories) {
        return maintenanceCalories + WEIGHTGAINCALORIES;
    }

    /**
     * Calculates the amount of calories needed for weight loss
     *
     * @param maintenanceCalories Calories needed for maintenance
     * @return Double value containing appropriate amount of calories
     */
    private double calculateWeightLoss(double maintenanceCalories) {
        return maintenanceCalories - WEIGHTLOSSCALORIES;
    }

}
