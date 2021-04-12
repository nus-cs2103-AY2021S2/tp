package seedu.cakecollate.testutil;

import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TYPE_CHOCOLATE;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TYPE_STRAWBERRY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.cakecollate.model.OrderItems;
import seedu.cakecollate.model.orderitem.OrderItem;

public class TypicalOrderItems {
    public static final OrderItem BLACK_FOREST = new OrderItemBuilder()
            .withType("Black Forest")
            .build();

    public static final OrderItem RED_VELVET = new OrderItemBuilder()
            .withType("Red Velvet Cake")
            .build();

    public static final OrderItem CHOCOLATE_MUD = new OrderItemBuilder()
            .withType("Chocolate Mud Cake")
            .build();

    public static final OrderItem OREO_CHEESE = new OrderItemBuilder()
            .withType("Oreo Cheesecake")
            .build();

    public static final OrderItem BUTTERSCOTCH = new OrderItemBuilder()
            .withType("Butterscotch Cake")
            .build();

    public static final OrderItem STRAWBERRY = new OrderItemBuilder()
            .withType(VALID_TYPE_STRAWBERRY)
            .build();

    public static final OrderItem CHOCOLATE = new OrderItemBuilder()
            .withType(VALID_TYPE_CHOCOLATE)
            .build();

    // manually added for testing
    public static final OrderItem DURIAN = new OrderItemBuilder()
            .withType("Durian")
            .build();

    public static final OrderItem CHENDOL = new OrderItemBuilder()
            .withType("Chendol")
            .build();

    public static final OrderItem ATYPICAL_ORDER_ITEM = new OrderItemBuilder()
            .withType("Unicorn")
            .build();

    private TypicalOrderItems() {
    } //prevents instantiation

    public static List<OrderItem> getTypicalOrderItems() {
        return new ArrayList<>(Arrays.asList(BLACK_FOREST, RED_VELVET,
                CHOCOLATE_MUD, OREO_CHEESE, BUTTERSCOTCH, STRAWBERRY, CHOCOLATE));
    }

    /**
     * Returns an {@code OrderItems} with all the typical order items.
     */
    public static OrderItems getTypicalOrderItemsModel() {
        OrderItems orderItems = new OrderItems();
        for (OrderItem orderItem : getTypicalOrderItems()) {
            orderItems.addOrderItem(orderItem);
        }
        return orderItems;
    }
}
