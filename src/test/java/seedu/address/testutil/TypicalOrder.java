package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_MOZZERLLA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.CARL;
import static seedu.address.testutil.TypicalCustomers.DANIEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            .build();

    public static final Order ORDER_FETA = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_FETA).withQuantity(VALID_QUANTITY_5)
            .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
            .withCustomerId(BENSON.getId()).withOrderId(3)
            .build();

    public static final Order ORDER_MOZZERLLA = new OrderBuilder()
            .withCheeseType(VALID_CHEESE_TYPE_MOZZERLLA).withQuantity(VALID_QUANTITY_2)
            .withOrderDate(VALID_ORDER_DATE_4).withCompletedDate(null)
            .withCustomerId(DANIEL.getId()).withOrderId(4)
            .build();

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_BRIE, ORDER_CAMEMBERT, ORDER_FETA, ORDER_MOZZERLLA));
    }
}
