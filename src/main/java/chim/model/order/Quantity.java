package chim.model.order;

import static chim.commons.util.AppUtil.checkArgument;

/**
 * Represents an Order's quantity in the Cheese Inventory Management System (CHIM).
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(int)}
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS = "Quantity must be a positive integer number.";

    public final int value;

    /**
     * Constructs an {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(int quantity) {
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        value = quantity;
    }

    /**
     * Returns if a given integer is a valid quantity.
     */
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    public int getQuantity() {
        return value;
    }

    public boolean isSameQuantity(int quantity) {
        return this.value == quantity;
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
