package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_GOUDA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZARELLA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_2;
import static seedu.address.testutil.TypicalCheese.CAMEMBERT;
import static seedu.address.testutil.TypicalCheese.CAMEMBERT_2;
import static seedu.address.testutil.TypicalCheese.FETA;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.CARL;
import static seedu.address.testutil.TypicalCustomers.DANIEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.cheese.CheeseId;
import seedu.address.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrder {
    public static final Order ORDER_BRIE = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_BRIE).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_3).withCompletedDate(null)
            .withCustomerId(CARL.getId()).withOrderId(1)
            .build();

    public static final Order ORDER_CAMEMBERT = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_1).withCompletedDate(VALID_COMPLETED_DATE_1)
            .withCustomerId(ALICE.getId()).withOrderId(2)
            .withCheeses(new HashSet<>(Arrays.asList(CAMEMBERT.getCheeseId())))
            .build();

    public static final Order ORDER_FETA = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_FETA).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
            .withCustomerId(BENSON.getId()).withOrderId(3)
            .withCheeses(new HashSet<>(Arrays.asList(FETA.getCheeseId())))
            .build();

    public static final Order ORDER_MOZZARELLA = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_MOZZARELLA).withQuantity(VALID_QUANTITY_2)
            .withOrderDate(VALID_ORDER_DATE_4).withCompletedDate(null)
            .withCustomerId(DANIEL.getId()).withOrderId(4)
            .build();

    public static final Order ORDER_CAMEMBERT_2 = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT).withQuantity(VALID_QUANTITY_1)
            .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
            .withCustomerId(BENSON.getId()).withOrderId(5)
            .withCheeses(new HashSet<CheeseId>(Arrays.asList(CAMEMBERT_2.getCheeseId())))
            .build();

    public static final Order ORDER_GOUDA = new OrderBuilder()
        .withCheeseType(VALID_CHEESE_TYPE_GOUDA).withQuantity(VALID_QUANTITY_1)
        .withOrderDate(VALID_ORDER_DATE_5).withCompletedDate(null)
        .withCustomerId(BENSON.getId()).withOrderId(6)
        .build();

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_BRIE, ORDER_CAMEMBERT, ORDER_FETA,
                ORDER_MOZZARELLA, ORDER_CAMEMBERT_2, ORDER_GOUDA));
    }
}
