package seedu.address.model.user;

/**
 * Represents a User's Bmi.
 */
public class Bmi {
    private static final double HEALTHY_BMI_LOWER_BOUND = 18.5;
    private static final double HEALTHY_BMI_UPPER_BOUND = 22.9;

    // Identity fields
    private final double weight;
    private final double height;

    // Data fields
    private final double bmi;

    /**
     * Creates the Bmi object representing the user.
     * @param weight Current input weight
     * @param height Current input height
     */
    public Bmi(double weight, double height) {
        this.weight = weight;
        this.height = height;
        this.bmi = calculateBmi();
    }

    /**
     * Returns the Double value representing the Bmi.
     * @return Double value representing the Bmi
     */
    protected double calculateBmi() {
        return this.weight / Math.pow(this.height, 2);
    }

    /**
     * Returns the lower bound of weight for the healthy Bmi.
     * @return Double value representing lower bound of healthy weight
     */
    protected double getLowerBoundWeight() {
        return HEALTHY_BMI_LOWER_BOUND * Math.pow(this.height, 2);
    }

    /**
     * Returns the upper bound of weight for the healthy Bmi.
     * @return Double value representing upper bound of healthy weight
     */
    protected double getUpperBoundWeight() {
        return HEALTHY_BMI_UPPER_BOUND * Math.pow(this.height, 2);
    }

    protected Double getHeight() {
        return this.height;
    }

    protected Double getWeight() {
        return this.weight;
    }

    protected Double getBmi() {
        return this.bmi;
    }

    @Override
    public String toString() {
        return String.valueOf(this.bmi);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Bmi)) {
            return false;
        }

        Bmi otherBmi = (Bmi) other;
        return otherBmi.getWeight().equals(getWeight())
                && otherBmi.getHeight().equals(getHeight())
                && otherBmi.getBmi().equals(getBmi());
    }

}
