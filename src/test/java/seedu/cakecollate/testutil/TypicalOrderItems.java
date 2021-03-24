package seedu.cakecollate.testutil;

import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_COST_CHOCOLATE;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_COST_STRAWBERRY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TYPE_CHOCOLATE;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TYPE_STRAWBERRY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.cakecollate.model.orderitem.OrderItem;

public class TypicalOrderItems {
    public static final OrderItem BLACK_FOREST = new OrderItemBuilder()
            .withType("Black Forest")
            .withCost("25.75")
            .build();

    public static final OrderItem RED_VELVET = new OrderItemBuilder()
            .withType("Red Velvet Cake")
            .withCost("34.99")
            .build();

    public static final OrderItem CHOCOLATE_MUD = new OrderItemBuilder()
            .withType("Chocolate Mud Cake")
            .withCost("40.95")
            .build();

    public static final OrderItem OREO_CHEESE = new OrderItemBuilder()
            .withType("Oreo Cheesecake")
            .withCost("25.75")
            .build();

    public static final OrderItem BUTTERSCOTCH = new OrderItemBuilder()
            .withType("Butterscotch Cake")
            .withCost("19.99")
            .build();

    public static final OrderItem STRAWBERRY = new OrderItemBuilder()
            .withType(VALID_TYPE_STRAWBERRY)
            .withCost(VALID_COST_STRAWBERRY)
            .build();

    public static final OrderItem CHOCOLATE = new OrderItemBuilder()
            .withType(VALID_TYPE_CHOCOLATE)
            .withCost(VALID_COST_CHOCOLATE)
            .build();

    private TypicalOrderItems() {
    } //prevents instantiation

    public static List<OrderItem> getTypicalOrderItems() {
        return new ArrayList<>(Arrays.asList(CHOCOLATE, STRAWBERRY,
                BUTTERSCOTCH, OREO_CHEESE, CHOCOLATE_MUD, RED_VELVET, BLACK_FOREST));
    }
}
