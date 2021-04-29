package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Session's fee per hour
 * Guarantees: immutable;
 */
public class Fee {

    public static final String MESSAGE_CONSTRAINTS = "Format of fee input is incorrect (Non-negative and maximum of "
        + "2 decimal places) or the given fee is unacceptable (Only accepts value between 0 to 999999.99).";
    private static final String VALIDATION_REGEX = "([1-9]\\d+|\\d)(\\.\\d{0,2}|)";

    private double fee;

    /**
     * Constructs a {@code Fee}.
     *
     * @param value The fee per hour for that specified session
     */
    public Fee(String value) {
        requireNonNull(value);
        checkArgument(isValidFee(value), MESSAGE_CONSTRAINTS);
        double fullFee = Double.parseDouble(value);
        this.fee = fullFee;
    }

    public double getFee() {
        return this.fee;
    }

    /**
     * Returns true if fee is valid
     */
    public static boolean isValidFee(String value) {
        return value.matches(VALIDATION_REGEX) && Double.parseDouble(value) <= 999999.99;
    }

    @Override
    public String toString() {
        return String.valueOf(fee);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Fee)) {
            return false;
        }

        Fee otherFee = (Fee) other;
        return Double.compare(otherFee.getFee(), getFee()) == 0;
    }
}
