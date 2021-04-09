package seedu.address.model.person.passenger;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the highest price a passenger is willing to pay for a ride.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price {


    public static final String MESSAGE_CONSTRAINTS =
            "The price you are willing to pay should be entered as a positive decimal number.";
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d\\d?)?";
    public final Double value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(double price) {
        checkArgument(isValidPrice(Double.toString(price)), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && value.equals(((Price) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
