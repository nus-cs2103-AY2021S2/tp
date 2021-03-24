package seedu.cakecollate.testutil;

import seedu.cakecollate.model.orderitem.Cost;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;

/**
 * A utility class to help with building Order Item objects.
 */
public class OrderItemBuilder {
    public static final String DEFAULT_TYPE = "Strawberry Cake";
    public static final String DEFAULT_COST = "20.75";

    private Type type;
    private Cost cost;

    /**
     * Creates a {@code OrderItemBuilder} with the default details.
     */
    public OrderItemBuilder() {
        type = new Type(DEFAULT_TYPE);
        cost = new Cost(DEFAULT_COST);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderItemBuilder(OrderItem orderItemToCopy) {
        type = orderItemToCopy.getType();
        cost = orderItemToCopy.getCost();
    }


    /**
     * Sets the {@code Type} of the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withCost(String cost) {
        this.cost = new Cost(cost);
        return this;
    }

    public OrderItem build() {
        return new OrderItem(type, cost);
    }
}
