package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrder {
    public static final Order ORDER_CAMEMBERT = new OrderBuilder()
        .withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT).withQuantity(VALID_QUANTITY_1)
        .withOrderDate(VALID_ORDER_DATE_1).withCompletedDate(VALID_COMPLETED_DATE_1)
        .withCustomerId(ALICE.getId()).withOrderId(1)
        .build();
    public static final Order ORDER_FETA = new OrderBuilder()
        .withCheeseType(VALID_CHEESE_TYPE_FETA).withQuantity(VALID_QUANTITY_5)
        .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
        .withCustomerId(BENSON.getId()).withOrderId(2)
        .build();

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_CAMEMBERT, ORDER_FETA));
    }
}
