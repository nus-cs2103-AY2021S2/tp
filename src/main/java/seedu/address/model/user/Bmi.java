package seedu.address.model.user;

/**
 * Represents a User's Bmi.
 */
public class Bmi {
    //@@author SoonKeatNeo
    public static final String MESSAGE_CONSTRAINTS =
            "Weight should be in kg and Height should be in cm.\nBoth should be greater than 1"
                    + " and may include a decimal.";
    private static final double HEALTHY_BMI_LOWER_BOUND = 18.5;
    private static final double HEALTHY_BMI_UPPER_BOUND = 22.9;
    private static final String VALIDATION_REGEX = "^(0)*[1-9](\\d+)?$|^(0)*[1-9](\\d+)?.(\\d+)$";

    // Identity fields

    private double weight;
    private double height;

    // Data fields
    private double bmi;

    public Bmi() {
        super();
    }

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
    public double calculateBmi() {
        return this.weight / Math.pow((this.height * 0.01), 2);
    }

    /**
     * Returns the lower bound of weight for the healthy Bmi.
     * @return Double value representing lower bound of healthy weight
     */
    public double getLowerBoundWeight() {
        return HEALTHY_BMI_LOWER_BOUND * Math.pow((this.height * 0.01), 2);
    }

    /**
     * Returns the upper bound of weight for the healthy Bmi.
     * @return Double value representing upper bound of healthy weight
     */
    public double getUpperBoundWeight() {
        return HEALTHY_BMI_UPPER_BOUND * Math.pow((this.height * 0.01), 2);
    }

    public Double getHeight() {
        return this.height;
    }

    public Double getWeight() {
        return this.weight;
    }

    public Double getBmi() {
        return this.bmi;
    }

    /**
     * Returns true if a given string is a valid weight or height.
     */
    public static boolean isValidWeightOrHeight(String test) {
        return test.matches(VALIDATION_REGEX);
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
