package seedu.cakecollate.model.orderitem;

import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;
import java.util.Objects;

/**
 * Represents an OrderItem.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OrderItem {

    private final Type type;
    private final Cost cost;

    /**
     * Every field must be present and not null.
     */
    public OrderItem(Type type, Cost cost) {
        requireAllNonNull(type, cost);
        this.type = type;
        this.cost = cost;
    }

    public Type getType() {
        return type;
    }

    public Cost getCost() {
        return cost;
    }

    /**
     * Returns true if both orders have the same type of cake.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrderItem(OrderItem otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.getType().equals(getType());
    }

    /**
     * Returns true if both order items have the same type of cake and cost.
     * This defines a stronger notion of equality between two orders.
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
        return otherOrder.getType().equals(getType())
                && otherOrder.getCost().equals(getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, cost);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Type of cake: ")
                .append(getType())
                .append("; Cost: ")
                .append(getCost());

        return builder.toString();
    }

}
