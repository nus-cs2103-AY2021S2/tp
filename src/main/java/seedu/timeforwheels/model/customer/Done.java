package seedu.timeforwheels.model.customer;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Customer's remark in the delivery list.
 * Guarantees: immutable; is always valid
 */
public class Done {

    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param done A valid string.
     */
    public Done(String done) {
        requireNonNull(done);
        value = done;
    }


    @Override
    public String toString() {
        return value;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Done // instanceof handles nulls
                && value.equals(((Done) other).value)); // state check
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
