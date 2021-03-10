package seedu.address.model.diet;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Wrapper class containing the composition for the various macronutrients.
 */
public final class MacroNutrientComposition {

    private final double fats;
    private final double carbohydrates;
    private final double proteins;

    /**
     * Constructor for diet composition.
     */
    public MacroNutrientComposition(double fats, double carbohydrates, double proteins) {
        checkArgument(isValidComposition(fats, carbohydrates, proteins));
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
    }

    /**
     * Returns the percentage for recommended fat consumption.
     *
     * @return Fat percentage.
     */
    public double getFats() {
        return fats;
    }

    /**
     * Returns the percentage for recommended protein consumption.
     *
     * @return Protein percentage.
     */
    public double getProteins() {
        return proteins;
    }

    /**
     * Returns the percentage for recommended carbohydrate consumption.
     *
     * @return Carbohydrate percentage.
     */
    public double getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Returns true if the sum of the macronutrient percentages totals to 100, and values given non-negative.
     *
     * @param fats The fat percentage.
     * @param carbohydrates The carbohydrate percentage.
     * @param proteins The protein percentage.
     * @return {@code boolean} true if valid composition, false otherwise.
     */
    public static boolean isValidComposition(double fats, double carbohydrates, double proteins) {
        return (fats + carbohydrates + proteins == 100)
                && (fats >= 0 && carbohydrates >= 0 && proteins >= 0);
    }

    @Override
    public String toString() {
        return String.format("{ \"proteins\": %.2f, \"fats\": %.2f, \"carbohydrates\": %.2f }",
                proteins, fats, carbohydrates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MacroNutrientComposition that = (MacroNutrientComposition) o;
        return Double.compare(that.fats, fats) == 0
                && Double.compare(that.carbohydrates, carbohydrates) == 0
                && Double.compare(that.proteins, proteins) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fats, carbohydrates, proteins);
    }

}
