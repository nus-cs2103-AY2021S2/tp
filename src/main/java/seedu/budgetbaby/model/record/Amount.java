package seedu.budgetbaby.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.AppUtil.checkArgument;

/**
 * Represents a Financial record's amount in the budget tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS =
        "Amount should be a positive number after rounding to 2 decimal places, and the upper limit for amount is 1,000,000.";
    public final Double value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param value A valid phone number.
     */
    public Amount(String value) {
        requireNonNull(value);
        checkArgument(isValidAmount(value), MESSAGE_CONSTRAINTS);
        this.value = parseValue(Double.parseDouble(value));
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidAmount(String test) {
        Boolean isValid = false;
        try {
            Double amount = parseValue(Double.parseDouble(test));

            System.out.println(amount);
            if (amount > 0 && amount <= 1000000) {
                isValid = true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return isValid;
    }

    /**
     * Returns an amount that has exactly 2 decimal places.
     */
    public static Double parseValue(Double value) {
        return (double) (Math.round(value * 100.0) / 100.0);
    }

    /**
     * Returns the value of the amount.
     */
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Amount // instanceof handles nulls
            && value.equals(((Amount) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
