package seedu.address.testutil;

import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.CustomerId;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Quantity;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final String DEFAULT_CHEESE_TYPE = "Feta";
    public static final int DEFAULT_QUANTITY = 1;
    public static final String DEFAULT_ORDER_DATE = "2021-02-03 12:00";
    public static final String DEFAULT_COMPLETED_DATE = "2021-03-04 15:00";
    public static final CustomerId DEFAULT_CUSTOMER_ID = CustomerId.getNextId(1);

    private CheeseType cheeseType;
    private Quantity quantity;
    private OrderDate orderDate;
    private CompletedDate completedDate;
    private OrderId orderId;
    private CustomerId customerId;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        this.cheeseType = CheeseType.getCheeseType(DEFAULT_CHEESE_TYPE);
        this.quantity = new Quantity(DEFAULT_QUANTITY);
        this.orderDate = new OrderDate(DEFAULT_ORDER_DATE);
        this.completedDate = new CompletedDate(DEFAULT_COMPLETED_DATE);
        this.orderId = null;
        this.customerId = DEFAULT_CUSTOMER_ID;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     * Makes an exact copy (with the same ID) of the {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        this.cheeseType = orderToCopy.getCheeseType();
        this.quantity = orderToCopy.getQuantity();
        this.orderDate = orderToCopy.getOrderDate();
        this.completedDate = orderToCopy.getCompletedDate().orElse(null);
        this.orderId = orderToCopy.getOrderId();
        this.customerId = orderToCopy.getCustomerId();
    }

    /**
     * Sets the {@code CheeseType} of the {@code Order} that we are building.
     */
    public OrderBuilder withCheeseType(String cheeseType) {
        this.cheeseType = CheeseType.getCheeseType(cheeseType);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Order} that we are building.
     */
    public OrderBuilder withQuantity(int quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code OrderDate} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderDate(String orderDate) {
        this.orderDate = new OrderDate(orderDate);
        return this;
    }

    /**
     * Sets the {@code CompletedDate} of the {@code Order} that we are building.
     */
    public OrderBuilder withCompletedDate(String completedDate) {
        if (completedDate == null) {
            this.completedDate = null;
        } else {
            this.completedDate = new CompletedDate(completedDate);
        }
        return this;
    }

    /**
     * Sets the {@code OrderId} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderId(int id) {
        this.orderId = new OrderId(id);
        return this;
    }

    /**
     * Sets the {@code OrderId} of the {@code Order} that we are building.
     */
    public OrderBuilder withOrderId(OrderId id) {
        this.orderId = id;
        return this;
    }

    /**
     * Sets the {@code OrderId} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomerId(CustomerId customerId) {
        this.customerId = customerId;
        return this;
    }

    /**
     * Returns the immutable {@code Order} object representing the data we have.
     * @return an {@code Order} representation of data
     */
    public Order build() {
        if (orderId == null) {
            return new Order(cheeseType, quantity, orderDate, completedDate, customerId);
        } else {
            return new Order(cheeseType, quantity, orderDate, completedDate, orderId, customerId);
        }
    }

}
