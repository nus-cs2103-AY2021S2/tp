package seedu.timeforwheels.model.customer;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Customer's remark in the delivery list.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public final String value;

    /**
     * Creates a Remark object to be associated with a Customer object.
     * @param remark a non null remark
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
