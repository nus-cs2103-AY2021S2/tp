package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseType;

public class Order {
    private static int currentId = 0;

    // Identity fields
    // Primary key for Order
    private final int orderId;
    private final CheeseType cheeseType;

    // Data fields
    private final OrderDate orderDate;
    private final CompletedDate completedDate;

    private final Set<Cheese> cheeses = new HashSet<>();

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType) {
        this(orderDate, completedDate, cheeseType, new HashSet<>());
    }

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType, Set<Cheese> cheeses) {
        this(orderDate, completedDate, cheeseType, cheeses, currentId++);
    }

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType, Order previousOrder) {
        this(orderDate, completedDate, cheeseType, new HashSet<>(), previousOrder.orderId);
    }

    public Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType,
                 Set<Cheese> cheeses, Order previousOrder) {
        this(orderDate, completedDate, cheeseType, cheeses, previousOrder.orderId);
    }

    private Order(OrderDate orderDate, CompletedDate completedDate, CheeseType cheeseType,
                  Set<Cheese> cheeses, int orderId) {
        requireAllNonNull(orderDate, cheeseType, cheeses);
        this.orderDate = orderDate;
        this.completedDate = completedDate;
        this.cheeseType = cheeseType;
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
        return cheeseType;
    }

    public int getOrderId() {
        return orderId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Cheese> getCheeses() {
        return Collections.unmodifiableSet(cheeses);
    }

    /**
     * Is same order
     * @param otherOrder
     * @return
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null && otherOrder.orderId == orderId;
    }


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
