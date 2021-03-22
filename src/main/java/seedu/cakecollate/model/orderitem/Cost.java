package seedu.cakecollate.model.orderitem;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.util.AppUtil.checkArgument;

/**
 * Represents the cost of an order item.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(double)}
 */
public class Cost {

    public static final String MESSAGE_CONSTRAINTS =
            "Order cost should only contain numbers and decimal point, and it should not be blank";

    public static final String VALIDATION_REGEX = "^([0-9]|([0-9]+[0-9\\.]*[0-9]))$";

    public final Double value;

    /**
     * Constructs an {@code Address}.
     *
     * @param cost A valid cost.
     */
    public Cost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        value = Double.parseDouble(cost);
    }

    /**
     * Returns true if a given string is a valid Cost.
     */
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && value.equals(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
