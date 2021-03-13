package seedu.address.model.user;

/**
 * Represents a User's ideal weight.
 */
public class IdealWeight {

    public static final String MESSAGE_CONSTRAINTS =
            "Ideal Weight should be in kilograms, greater than 1 and may include a decimal!";
    private static final String VALIDATION_REGEX = "^[1-9](\\d+)?$|^[1-9](\\d+)?.(\\d+)$";
    private final double ideal_weight;

    /**
     * Initializes the ideal weight class.
     * @param ideal_weight Ideal weight to be input
     */
    public IdealWeight(double ideal_weight) {
        this.ideal_weight = ideal_weight;
    }

    public double getIdealWeight() {
        return this.ideal_weight;
    }

    /**
     * Returns true if a given string is a valid ideal weight.
     */
    public static boolean isValidIdealWeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(this.ideal_weight);
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
