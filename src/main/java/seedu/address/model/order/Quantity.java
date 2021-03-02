package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Quantity {
    public static final String MESSAGE_CONSTRAINTS = "Quantity must be a positive number.";

    public final int value;

    /**
     * Constructs an {@code Email}.
     *
     * @param quantity A valid email address.
     */
    public Quantity(int quantity) {
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        value = quantity;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Quantity // instanceof handles nulls
            && value == ((Quantity) other).value); // state check
    }
}
