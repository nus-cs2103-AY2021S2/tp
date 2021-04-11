package seedu.address.model.user;

/**
 * Represents a User's ideal weight.
 */
public class IdealWeight {

    public static final String MESSAGE_CONSTRAINTS =
            "Ideal Weight should be in kilograms, greater than 1 and may include a decimal!";
    private static final String VALIDATION_REGEX = "^(0)*[1-9](\\d+)?$|^(0)*[1-9](\\d+)?.(\\d+)$";
    private double idealWeight;

    public IdealWeight() {
        super();
    }

    /**
     * Initializes the ideal weight class.
     * @param idealWeight Ideal weight to be input
     */
    public IdealWeight(double idealWeight) {
        this.idealWeight = idealWeight;
    }

    public double getIdealWeight() {
        return this.idealWeight;
    }

    /**
     * Returns true if a given string is a valid ideal weight.
     */
    public static boolean isValidIdealWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(this.idealWeight);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof IdealWeight)) {
            return false;
        }
        IdealWeight otherIdealWeight = (IdealWeight) other;
        return otherIdealWeight.getIdealWeight() == getIdealWeight();
    }

}
