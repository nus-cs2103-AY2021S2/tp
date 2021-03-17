package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class OrderDescription {

    public static final String MESSAGE_CONSTRAINTS = "Order description should not be left blank.";

    /*
     * The first character of the order description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    // todo need help, why is this able to catch o/1 x Amy errors (errors using prefix when there shouldnt be)

    public final String value;

    /**
     * Constructs an {@code Order Description}.
     *
     * @param orderDescription A valid order description
     */
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
                || (other instanceof seedu.address.model.order.OrderDescription // instanceof handles nulls
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
