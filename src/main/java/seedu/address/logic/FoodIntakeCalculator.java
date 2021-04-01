package seedu.address.logic;

/**
 * Represents a class to calculate the calories based on total nutrients values input.
 */
public class FoodIntakeCalculator {
    public static final double PROTEIN_AND_CARBOS_MULTIPLIER = 4;
    public static final double FAT_MULTIPLIER = 9;

    private final double calories;

    public FoodIntakeCalculator(double carbos, double fats, double proteins) {
        this.calories = calculateCalories(carbos, fats, proteins);
    }

    public double getCalories() {
        return this.calories;
    }

    /**
     * Calculates calories based on total nutrients input.
     *
     * @param carbos   carbos value
     * @param fats     fats value
     * @param proteins proteins value
     * @return calories value
     */
    public double calculateCalories(double carbos, double fats, double proteins) {
        double convertedCarbos = carbos * PROTEIN_AND_CARBOS_MULTIPLIER;
        double convertedFats = fats * FAT_MULTIPLIER;
        double convertedProteins = proteins * PROTEIN_AND_CARBOS_MULTIPLIER;
        double totalKiloCalories = convertedCarbos + convertedFats + convertedProteins;
        return totalKiloCalories;
    }
}
