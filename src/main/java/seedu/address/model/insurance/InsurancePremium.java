package seedu.address.model.insurance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the yearly premium amount of a client's insurance plan.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class InsurancePremium {

    public static final String MESSAGE_CONSTRAINTS =
            "The insurance premium amount should be a positive integer without the $ sign.";

    public final String amount;

    /**
     * Constructs a {@code InsurancePremium}.
     *
     * @param amount A valid amount.
     */
    public InsurancePremium(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    /**
     * Returns true if a given amount is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        if (test == null) {
            return false;
        }
        try {
            Integer i = Integer.parseInt(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return amount;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePremium // instanceof handles nulls
                && amount.equals(((InsurancePremium) other).amount)); // state check
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

}
