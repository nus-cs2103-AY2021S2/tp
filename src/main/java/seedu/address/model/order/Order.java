package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.cheese.CheeseType;

/**
 * Represents a Order in the Cheese inventory Management System (CHIM).
 * Guarantees: details are present and not null, field values are validated.
 */
public class Order {
    private static int currentId = 0;

    // Identity fields
    // Primary key for Order
    private final int orderId;
    private final CheeseType orderCheeseType;

    // Data fields
    private final OrderDate orderDate;
    private final CompletedDate completedDate;

    // Set of cheese IDs for this order
    private final Set<Integer> cheeses = new HashSet<>();

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType) {
        this(orderDate, completedDate, cheeseType, new HashSet<>());
    }

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType, Set<Integer> cheeses) {
        this(orderDate, completedDate, cheeseType, cheeses, currentId++);
    }

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType, Order previousOrder) {
        this(orderDate, completedDate, cheeseType, new HashSet<>(), previousOrder.orderId);
    }

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType,
                 Set<Integer> cheeses, Order previousOrder) {
        this(orderDate, completedDate, cheeseType, cheeses, previousOrder.orderId);
    }

    private Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType,
                  Set<Integer> cheeses, int orderId) {
        requireAllNonNull(orderDate, cheeseType, cheeses);
        this.orderDate = orderDate;
        this.completedDate = completedDate;
        this.orderCheeseType = cheeseType;
        this.orderId = orderId;
        this.cheeses.addAll(cheeses);
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    public CompletedDate getCompletedDate() {
        return completedDate;
    }

    public CheeseType getCheeseType() {
        return orderCheeseType;
    }

    public int getOrderId() {
        return orderId;
    }

    /**
     * Returns an immutable Cheese Ids set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Integer> getCheeses() {
        return Collections.unmodifiableSet(cheeses);
    }

    /**
     * Returns true if both orders have the same name.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null && otherOrder.orderId == orderId;
    }

    /**
     * Returns true if both orders have the same identity and data fields.
     * This defines a stronger notion of equality between two orders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getOrderId() == getOrderId()
                && otherOrder.getCheeseType().equals(getCheeseType())
                && otherOrder.getOrderDate().equals(getOrderDate())
                && otherOrder.getCompletedDate().equals(getCompletedDate())
                && otherOrder.getCheeses().equals(getCheeses());
    }
}
