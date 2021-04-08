package chim.testutil;

import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_GOUDA;
import static chim.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZARELLA;
import static chim.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static chim.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_3;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_4;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_5;
import static chim.logic.commands.CommandTestUtil.VALID_QUANTITY_1;
import static chim.logic.commands.CommandTestUtil.VALID_QUANTITY_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import chim.model.cheese.CheeseId;
import chim.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrder {
    public static final Order ORDER_BRIE = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_BRIE).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_3).withCompletedDate(null)
            .withCustomerId(TypicalCustomers.CARL.getId()).withOrderId(1)
            .build();

    public static final Order ORDER_CAMEMBERT = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_1).withCompletedDate(VALID_COMPLETED_DATE_1)
            .withCustomerId(TypicalCustomers.ALICE.getId()).withOrderId(2)
            .withCheeses(new HashSet<>(Arrays.asList(TypicalCheese.CAMEMBERT.getCheeseId())))
            .build();

    public static final Order ORDER_FETA = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_FETA).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
            .withCustomerId(TypicalCustomers.BENSON.getId()).withOrderId(3)
            .withCheeses(new HashSet<>(Arrays.asList(TypicalCheese.FETA.getCheeseId())))
            .build();

    public static final Order ORDER_MOZZARELLA = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_MOZZARELLA).withQuantity(VALID_QUANTITY_2)
            .withOrderDate(VALID_ORDER_DATE_4).withCompletedDate(null)
            .withCustomerId(TypicalCustomers.DANIEL.getId()).withOrderId(4)
            .build();

    public static final Order ORDER_CAMEMBERT_2 = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
            .withCustomerId(TypicalCustomers.BENSON.getId()).withOrderId(5)
            .withCheeses(new HashSet<CheeseId>(Arrays.asList(TypicalCheese.CAMEMBERT_2.getCheeseId())))
            .build();

    public static final Order ORDER_GOUDA = new OrderBuilder()
        .withCheeseType(VALID_CHEESE_TYPE_GOUDA).withQuantity(VALID_QUANTITY_1)
        .withOrderDate(VALID_ORDER_DATE_5).withCompletedDate(null)
        .withCustomerId(TypicalCustomers.BENSON.getId()).withOrderId(6)
        .build();

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_BRIE, ORDER_CAMEMBERT, ORDER_FETA,
                ORDER_MOZZARELLA, ORDER_CAMEMBERT_2, ORDER_GOUDA));
    }
}
