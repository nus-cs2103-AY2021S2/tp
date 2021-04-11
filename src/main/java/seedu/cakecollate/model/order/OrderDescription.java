package seedu.cakecollate.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.cakecollate.commons.util.AppUtil.checkArgument;

import seedu.cakecollate.model.orderitem.Type;

public class OrderDescription {
    public static final String MESSAGE_OVERFLOW = "Order description has a size limit of 70 characters.";

    public static final String MESSAGE_CONSTRAINTS =
            String.format(Type.SHARED_CONSTRAINTS_MESSAGE, "Order description");
    public static final String MESSAGE_EMPTY = "Order description cannot be empty.";

    public static final String VALIDATION_REGEX = Type.VALIDATION_REGEX;

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
                || (other instanceof seedu.cakecollate.model.order.OrderDescription // instanceof handles nulls
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

    public String getValue() {
        return value;
    }
}
