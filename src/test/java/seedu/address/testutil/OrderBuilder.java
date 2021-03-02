package seedu.address.testutil;

import seedu.address.model.cheese.CheeseType;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;

public class OrderBuilder {

    public static final String DEFAULT_ORDER_DATE = "2021-02-03 12:00";
    public static final String DEFAULT_COMPLETED_DATE = "2021-03-04 15:00";
    public static final String DEFAULT_CHEESE_TYPE = "Feta";

    private OrderDate orderDate;
    private CompletedDate completedDate;
    private CheeseType cheeseType;
    private final Order orderToCopy;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public OrderBuilder() {
        this.orderDate = new OrderDate(DEFAULT_ORDER_DATE);
        this.completedDate = new CompletedDate(DEFAULT_COMPLETED_DATE);
        this.cheeseType = CheeseType.getCheeseType(DEFAULT_CHEESE_TYPE);
        this.orderToCopy = null;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        this.orderDate = orderToCopy.getOrderDate();
        this.completedDate = orderToCopy.getCompletedDate();
        this.cheeseType = orderToCopy.getCheeseType();
        this.orderToCopy = orderToCopy;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public OrderBuilder withOrderDate(String orderDate) {
        this.orderDate = new OrderDate(orderDate);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public OrderBuilder withCompletedDate(String completedDate) {
        this.completedDate = new CompletedDate(completedDate);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public OrderBuilder withCheeseType(String cheeseType) {
        this.cheeseType = CheeseType.getCheeseType(cheeseType);
        return this;
    }

    /**
     * Build order object
     * @return
     */
    public Order build() {
        if (orderToCopy == null) {
            return new Order(orderDate, completedDate, cheeseType);
        } else {
            return new Order(orderDate, completedDate, cheeseType, orderToCopy);
        }
    }

}
