package seedu.cakecollate.testutil;

import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;

/**
 * A utility class to help with building Order Item objects.
 */
public class OrderItemBuilder {
    public static final String DEFAULT_TYPE = "Strawberry Cake";

    private Type type;

    /**
     * Creates a {@code OrderItemBuilder} with the default details.
     */
    public OrderItemBuilder() {
        type = new Type(DEFAULT_TYPE);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderItemBuilder(OrderItem orderItemToCopy) {
        type = orderItemToCopy.getType();
    }


    /**
     * Sets the {@code Type} of the {@code OrderItem} that we are building.
     */
    public OrderItemBuilder withType(String type) {
        this.type = new Type(type);
        return this;
    }

    public OrderItem build() {
        return new OrderItem(type);
    }
}
