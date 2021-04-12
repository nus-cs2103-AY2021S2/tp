package seedu.cakecollate.model.orderitem;

import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an OrderItem.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OrderItem {

    private final Type type;

    /**
     * Returns
     *
     * Type field must be present and not null.
     */
    public OrderItem(Type type) {
        requireAllNonNull(type);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    /**
     * Returns true if both order items have the same type of cake.
     * This method is case insensitive.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderItem)) {
            return false;
        }

        OrderItem otherOrder = (OrderItem) other;
        return otherOrder.getType().equals(getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getType());
        return builder.toString();
    }

}
