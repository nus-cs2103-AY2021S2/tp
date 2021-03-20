package seedu.address.logic;

public class DailyFoodIntakeCalculator {
    public static final double PROTEIN_AND_CARBOS_MULTIPLIER = 4;
    public static final double FAT_MULTIPLIER = 9;

    private final double calories;

    public DailyFoodIntakeCalculator (double carbos, double fats, double proteins) {
        this.calories = calculateCalories(carbos, fats, proteins);
    }

    public double getCalories() {
        return this.calories;
    }

    public double calculateCalories(double carbos, double fats, double proteins) {
        double convertedCarbos = carbos * PROTEIN_AND_CARBOS_MULTIPLIER;
        double convertedFats = fats * FAT_MULTIPLIER;
        double convertedProteins = proteins * PROTEIN_AND_CARBOS_MULTIPLIER;
        double totalKiloCalories = convertedCarbos + convertedFats + convertedProteins;
        return totalKiloCalories;
    }
}
