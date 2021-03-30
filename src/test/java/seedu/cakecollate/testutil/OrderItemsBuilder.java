package seedu.cakecollate.testutil;

import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.orderitem.OrderItem;


public class OrderItemsBuilder {

    private OrderItems orderItems;

    public OrderItemsBuilder() {
        orderItems = new OrderItems();
    }

    public OrderItemsBuilder(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * Adds a new {@code OrderItem} to the {@code OrderItems} that we are building.
     */

    public OrderItemsBuilder withOrderItem(OrderItem item) {
        orderItems.addOrderItem(item);
        return this;
    }

    public OrderItems build() {
        return orderItems;
    }
}
