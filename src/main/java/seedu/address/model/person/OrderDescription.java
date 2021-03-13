package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

// todo im not sure if this class name can be improved
// todo javadocs after fixing class name
public class OrderDescription {

    public static final String MESSAGE_CONSTRAINTS = "Order description should not be left blank.";

    public final String value;

    /*
     * The first character of the order description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*"; // todo need help, why is this catching o/1 x Amy errors (errors using prefix when there shouldnt be)

    public OrderDescription(String orderDescription) {
        requireNonNull(orderDescription);
        checkArgument(isValidOrderDescription(orderDescription), MESSAGE_CONSTRAINTS);
        this.value = orderDescription;
    }

    /**
     * Returns true if a given string is a order desecription.
     */
    public static boolean isValidOrderDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.OrderDescription // instanceof handles nulls
                && value.equals(((OrderDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }
}
