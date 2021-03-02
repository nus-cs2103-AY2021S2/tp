package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_FETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;

import seedu.address.model.order.Order;

public class TypicalOrder {
    public static final Order ORDER_CAMEMBERT = new OrderBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
        .withOrderDate(VALID_ORDER_DATE_1).withCompletedDate(VALID_COMPLETED_DATE_1)
        .build();
    public static final Order ORDER_FETA = new OrderBuilder().withCheeseType(VALID_CHEESE_TYPE_FETA)
        .withOrderDate(VALID_ORDER_DATE_2).withCompletedDate(VALID_COMPLETED_DATE_2)
        .build();
}
