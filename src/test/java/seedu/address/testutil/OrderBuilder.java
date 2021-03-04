package seedu.address.testutil;

import seedu.address.model.cheese.CheeseType;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final String DEFAULT_ORDER_DATE = "2021-02-03 12:00";
    public static final String DEFAULT_COMPLETED_DATE = "2021-03-04 15:00";
    public static final String DEFAULT_CHEESE_TYPE = "Feta";

    private OrderDate orderDate;
    private CompletedDate completedDate;
    private CheeseType cheeseType;
    private final Order orderToCopy;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        this.orderDate = new OrderDate(DEFAULT_ORDER_DATE);
        this.completedDate = new CompletedDate(DEFAULT_COMPLETED_DATE);
        this.cheeseType = CheeseType.getCheeseType(DEFAULT_CHEESE_TYPE);
        this.orderToCopy = null;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     * Makes an exact copy (with the same ID) of the {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        this.orderDate = orderToCopy.getOrderDate();
        this.completedDate = orderToCopy.getCompletedDate();
        this.cheeseType = orderToCopy.getCheeseType();
        this.orderToCopy = orderToCopy;
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
        this.completedDate = new CompletedDate(completedDate);
        return this;
    }

    /**
     * Sets the {@code CheeseType} of the {@code Order} that we are building.
     */
    public OrderBuilder withCheeseType(String cheeseType) {
        this.cheeseType = CheeseType.getCheeseType(cheeseType);
        return this;
    }

    /**
     * Returns the immutable {@code Order} object representing the data we have.
     * @return an {@code Order} representation of data
     */
    public Order build() {
        if (orderToCopy == null) {
            return new Order(orderDate, completedDate, cheeseType);
        } else {
            return new Order(orderDate, completedDate, cheeseType, orderToCopy);
        }
    }

}
