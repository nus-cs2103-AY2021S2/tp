package seedu.address.model.order;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.CustomerId;

/**
 * Represents a Order in the Cheese Inventory Management System (CHIM).
 * Guarantees: details are present and not null, field values are validated.
 */
public class Order {

    // Identity fields
    // Primary key for Order
    private final OrderId orderId;
    private final CheeseType orderCheeseType;
    private final Quantity quantity;
    private final CustomerId customerId;

    // Data fields
    private final OrderDate orderDate;
    private final Optional<CompletedDate> completedDate;

    // Set of cheese IDs for this order
    private final Set<CheeseId> cheeses = new HashSet<>();

    public Order(CheeseType cheeseType, Quantity quantity, OrderDate orderDate,
                 CompletedDate completedDate, CustomerId customerId) {
        this(cheeseType, quantity, orderDate, completedDate, new HashSet<>(), customerId);
    }

    /**
     * Initializes an incomplete order.
     * Completed date is set to null to indicate the order is incomplete.
     */
    public Order(CheeseType cheeseType, Quantity quantity, OrderDate orderDate, CustomerId customerId) {
        this(cheeseType, quantity, orderDate, null, new HashSet<>(),
                OrderId.getNextId(), customerId);
    }

    /**
     * Initializes a complete order with date of completion and the IDs of cheeses used to fulfil the order.
     */
    public Order(CheeseType cheeseType, Quantity quantity, OrderDate orderDate, CompletedDate completedDate,
                 Set<CheeseId> cheeses, CustomerId customerId) {
        this(cheeseType, quantity, orderDate, completedDate, cheeses, OrderId.getNextId(), customerId);
    }

    public Order(CheeseType cheeseType, Quantity quantity, OrderDate orderDate, CompletedDate completedDate,
                 OrderId orderId, CustomerId customerId) {
        this(cheeseType, quantity, orderDate, completedDate, new HashSet<>(), orderId, customerId);
    }

    /**
     * Initializes a complete order with existing order ,
     * date of completion and the IDs of cheeses used to fulfil the order.
     */
    public Order(Order orderToUpdate, CompletedDate completedDate, Set<CheeseId> cheeses) {
        this(orderToUpdate.orderCheeseType, orderToUpdate.quantity,
                orderToUpdate.orderDate, completedDate, cheeses,
                orderToUpdate.orderId, orderToUpdate.customerId);
    }

    /**
     * Every field must be present and not null.
     */
    public Order(CheeseType cheeseType, Quantity quantity, OrderDate orderDate, CompletedDate completedDate,
                 Set<CheeseId> cheeses, OrderId orderId, CustomerId customerId) {
        requireAllNonNull(cheeseType, quantity, orderDate, cheeses, orderId, customerId);
        checkOrderArguments(cheeseType, quantity, orderDate, completedDate, cheeses, orderId, customerId);
        this.orderCheeseType = cheeseType;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.completedDate = Optional.ofNullable(completedDate);
        this.orderId = orderId;
        this.customerId = customerId;
        this.cheeses.addAll(cheeses);
    }

    public CheeseType getCheeseType() {
        return orderCheeseType;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    public Optional<CompletedDate> getCompletedDate() {
        return completedDate;
    }

    public boolean isComplete() {
        // If an order is incomplete, the date must have been set to empty
        return completedDate.isPresent();
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    /**
     * Returns an immutable Cheese Ids set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<CheeseId> getCheeses() {
        return Collections.unmodifiableSet(cheeses);
    }

    /**
     * Checks whether the given parameters are valid for an order
     */
    public static void checkOrderArguments(CheeseType cheeseType, Quantity quantity, OrderDate orderDate,
                                           CompletedDate completedDate, Set<CheeseId> cheeses, OrderId orderId,
                                           CustomerId customerId) {
        if (completedDate == null) {
            // Checks for incomplete orders
            checkArgument(cheeses.size() == 0, "The cheese set for an incomplete order should be empty.");
        } else {
            // Checks for completed orders
            checkArgument(completedDate.isAfter(orderDate), "The completed date of an order should be after the"
                + " order date.");
            checkArgument(cheeses.size() == quantity.value, "The number of cheeses in the order does not"
                + " match the specified quantity.");
        }
    }

    /**
     * Returns true if both orders have the same name.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.orderId.equals(orderId)
                && otherOrder.customerId.equals(customerId);
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

        return otherOrder.getOrderId().equals(getOrderId())
                && otherOrder.getCustomerId().equals(getCustomerId())
                && otherOrder.getCheeseType().equals(getCheeseType())
                && otherOrder.getQuantity().equals(getQuantity())
                && otherOrder.getOrderDate().equals(getOrderDate())
                && otherOrder.getCompletedDate().equals(getCompletedDate())
                && otherOrder.getCheeses().equals(getCheeses());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getOrderId())
            .append("; Cheese Type: ")
            .append(getCheeseType())
            .append("; Quantity: ")
            .append(getQuantity())
            .append("; Order Date: ")
            .append(getOrderDate())
            .append("; Completed Date: ")
            .append(getCompletedDate().map(x -> x.toString()).orElse("-"))
            .append("; Customer ID: ")
            .append(getCustomerId())
            .append("; Cheese IDs: ")
            .append(getCheeses());

        return builder.toString();
    }

}
