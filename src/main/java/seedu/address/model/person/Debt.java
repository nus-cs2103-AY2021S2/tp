package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Debt stores a float that should be up to only 2 decimal places.
 * The user can only input in positive floats but the program is allowed to store negative floats.
 * A negative debt would indicate that the Person owes money to the user.
 */
public class Debt {

    public static final String MESSAGE_CONSTRAINTS = "Debt given should be a positive float and "
            + "should only be up 2 decimal places.";

    public final Float value;
    /**
     * Constructs a {@code Debt}
     * @param debt a valid debt
     */
    public Debt(String debt) {
        requireNonNull(debt);
        checkArgument(isValidDebt(debt), MESSAGE_CONSTRAINTS);
        value = Float.valueOf(debt);
    }

    /**
     * Constructs a {@code Debt}.
     * For testing purposes only.
     * @param debt
     */
    protected Debt(Float debt) {
        requireNonNull(debt);
        value = debt;
    }

    private static int getPrecision(Float debt) {
        String debtString = debt.toString();
        int decimalIndex = debtString.indexOf(".");
        return debtString.length() - decimalIndex - 1;
    }

    /**
     * Check whether the given string has a valid format for {@code Debt}
     * @param debt
     * @return boolean which indicate if string is a valid debt.
     */
    public static boolean isValidDebt(String debt) {
        try {
            Float debtFloat = Float.valueOf(debt);
            return getPrecision(debtFloat) <= 2;
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
        return new Debt(first.value + second.value);
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
        return new Debt(first.value - second.value);
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
