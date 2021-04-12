package seedu.storemando.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's quantity number in the storemando.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}.
 *
 */
public class Quantity {


    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 1000000;
    public static final String MESSAGE_CONSTRAINTS =
        "Quantity specified must be greater than 0 and must not exceed 1,000,000.";
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity number.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        assert(Long.valueOf(quantity) > 0);
        value = Long.valueOf(quantity).toString();
    }

    /**
     * Returns true if a given string is a valid quantity number.
     */
    public static boolean isValidQuantity(String test) {
        try {
            return test.matches(VALIDATION_REGEX) && Long.valueOf(test) > MIN_VALUE && Long.valueOf(test) <= MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Quantity // instanceof handles nulls
            && value.equals(((Quantity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int compare(Quantity anotherQuantity) {
        return Integer.parseInt(this.value) - Integer.parseInt(anotherQuantity.value);
    }
}
