package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;

/**
 * Debt stores a float that should be up to only 2 decimal places.
 * The user can only input in positive floats but the program is allowed to store negative floats.
 * A negative debt would indicate that the Person owes money to the user.
 */
public class Debt {

    public static final Debt MAX_DEBT = new Debt(new BigDecimal("999999999999"));
    public static final Debt MIN_DEBT = new Debt(new BigDecimal("-999999999999"));
    public static final String MESSAGE_CONSTRAINTS = "Debt given should be a positive float,"
            + "should only be up 2 decimal places and is smaller than " + MAX_DEBT;

    public final BigDecimal value;
    /**
     * Constructs a {@code Debt}
     * @param debt a valid debt
     */
    public Debt(String debt) {
        requireNonNull(debt);
        checkArgument(isValidDebt(debt), MESSAGE_CONSTRAINTS);
        value = new BigDecimal(debt);
    }

    /**
     * Constructs a {@code Debt}.
     * @param debt
     */
    protected Debt(BigDecimal debt) {
        requireNonNull(debt);
        value = debt;
    }

    /**
     * Check whether the given string has a valid format for {@code Debt}
     * @param debt
     * @return boolean which indicate if string is a valid debt.
     */
    public static boolean isValidDebt(String debt) {
        try {
            BigDecimal debtNumber = new BigDecimal(debt);
            return debtNumber.scale() <= 2
                    && debtNumber.compareTo(MAX_DEBT.value) != 1
                    && debtNumber.compareTo(MIN_DEBT.value) != -1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Adds two {@code Debt} objects.
     * @param first
     * @param second
     * @return Debt with the value being the sum of the two given Debt.
     */
    public static Debt add(Debt first, Debt second) {
        requireNonNull(first);
        requireNonNull(second);
        return new Debt(first.value.add(second.value));
    }

    /**
     * Subtract the second {@Debt} object from the first.
     * @param first
     * @param second
     * @return Debt with the value being first.value - second.value.
     */
    public static Debt subtract(Debt first, Debt second) {
        requireNonNull(first);
        requireNonNull(second);
        return new Debt(first.value.subtract(second.value));
    }

    /**
     * Compares this Debt with the specified debt.
     * @param debt value to which this Debt is being compared to
     * @return -1, 0 or 1 if this Debt is smaller than, equal to or greater than debt respectively
     */
    public int compareTo(Debt debt) {
        return this.value.compareTo(debt.value);
    }

    public String toUi() {
        return String.format("%.2f", value);
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Debt
                && (value.equals(((Debt) other).value)));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
