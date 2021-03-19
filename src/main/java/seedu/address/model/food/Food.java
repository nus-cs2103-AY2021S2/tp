package seedu.address.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Food {

    public static final double PROTEIN_AND_CARBOS_MULTIPLIER = 4; //Conversion to KCAL
    public static final double FAT_MULTIPLIER = 9; //Conversion to KCAL
    public static final String VALIDATION_WHITESPACE_REGEX = "[^\\s].*";
    public static final String VALIDATION_CHAR_REGEX = "[a-zA-Z ]*";
    public static final String VALIDATION_POSITIVE_DOUBLE_REGEX = "(\\d*\\.?\\d+)";
    public static final String MESSAGE_CONSTRAINTS = "Food name can take any alphabets charcter and it should not be"
            + " blank.";
    public static final String MESSAGE_DIGIT_CONSTRAINTS = "Double value input can only be positive and at least 0.";

    private final String name;
    private double fats;
    private double carbos;
    private double proteins;
    private double kiloCalories;

    /**
     * Initialises the food class.
     *
     * @param name     food name
     * @param carbos   amount of carbos
     * @param fats     amount of fats
     * @param proteins amount of proteins
     */
    public Food(String name, double carbos, double fats, double proteins) {
        requireNonNull(name);
        checkArgument(isValidFoodName(name), MESSAGE_CONSTRAINTS); //Checks for all whitespaces and valid character
        checkArgument(isValidNumber(fats, carbos, proteins), MESSAGE_DIGIT_CONSTRAINTS); //Checks for positive doubles.
        this.name = name.toLowerCase();
        this.fats = fats;
        this.carbos = carbos;
        this.proteins = proteins;
        this.kiloCalories = calculateKiloCalories();
    }

    /**
     * Gets food name.
     *
     * @return food name
     */
    public String getName() {
        return this.name;
    }

    public double getFats() {
        return this.fats;
    }

    public double getCarbos() {
        return this.carbos;
    }

    public double getProteins() {
        return this.proteins;
    }

    public double getKiloCalories() {
        return this.kiloCalories;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidFoodName(String test) {
        if (test.matches(VALIDATION_CHAR_REGEX) && test.matches(VALIDATION_WHITESPACE_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if a given number is valid and more than 0.
     */
    public static boolean isValidNumber(double fats, double carbos, double proteins) {
        String fatsString = Double.toString(fats);
        String carbosString = Double.toString(carbos);
        String proteinsString = Double.toString(proteins);
        if (fatsString.matches(VALIDATION_POSITIVE_DOUBLE_REGEX)
                && carbosString.matches(VALIDATION_POSITIVE_DOUBLE_REGEX)
                && proteinsString.matches(VALIDATION_POSITIVE_DOUBLE_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the amount of fats for this food.
     *
     * @param fats new amount of fats
     * @return updated food item
     */
    public Food updateFats(double fats) {
        this.fats = fats;
        updateKiloCalories();
        return this;
    }

    /**
     * Updates the amount of carbos for this food.
     *
     * @param carbos new carbo value
     * @return updated food item
     */

    public Food updateCarbos(double carbos) {
        this.carbos = carbos;
        updateKiloCalories();
        return this;
    }

    /**
     * Updates the amount of proteins for this food.
     *
     * @param proteins new protein value
     * @return updated food item
     */
    public Food updateProteins(double proteins) {
        this.proteins = proteins;
        updateKiloCalories();
        return this;
    }

    /**
     * Updates kilocalories each time a nutrient value is updated.
     */
    public void updateKiloCalories() {
        this.kiloCalories = calculateKiloCalories();
    }

    /**
     * Calculates total kilocalories based on input fats, carbos and proteins.
     *
     * @return total converted energy in kilocalories
     */
    public double calculateKiloCalories() {
        double convertedFats = this.fats * FAT_MULTIPLIER;
        double convertedCarbos = this.carbos * PROTEIN_AND_CARBOS_MULTIPLIER;
        double convertedProteins = this.proteins * PROTEIN_AND_CARBOS_MULTIPLIER;
        double totalKiloCalories = convertedCarbos + convertedFats + convertedProteins;
        return totalKiloCalories;
    }

    @Override
    public String toString() {
        String result = this.name + " (Carbohydrates: " + this.carbos + "g, Fats: " + this.fats + "g, Proteins: "
                + this.proteins + "g)";
        return result;
    }
}
