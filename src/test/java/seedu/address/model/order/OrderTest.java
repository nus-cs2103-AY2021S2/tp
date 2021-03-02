package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.testutil.TypicalOrder.ORDER_CAMEMBERT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Order order = new OrderBuilder().build();
    }

    @Test
    public void isSameOrder() {
        assertTrue(ORDER_CAMEMBERT.isSameOrder(ORDER_CAMEMBERT));

        assertFalse(ORDER_CAMEMBERT.isSameOrder(null));

        Order editedOrder =
            new OrderBuilder(ORDER_CAMEMBERT).withOrderDate("2021-09-04")
                .withCheeseType("Brie").build();
        assertTrue(ORDER_CAMEMBERT.isSameOrder(editedOrder));

        Order newSimilarOrder =
            new OrderBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withOrderDate(VALID_ORDER_DATE_1)
                .withCompletedDate(VALID_COMPLETED_DATE_1)
                .build();
        assertFalse(ORDER_CAMEMBERT.isSameOrder(newSimilarOrder));
    }

    @Test
    public void equals() {
        Order camembertOrderCopy = new OrderBuilder(ORDER_CAMEMBERT).build();
        assertTrue(ORDER_CAMEMBERT.equals(camembertOrderCopy));

        assertTrue(ORDER_CAMEMBERT.equals(ORDER_CAMEMBERT));

        assertFalse(ORDER_CAMEMBERT.equals(null));

        assertFalse(ORDER_CAMEMBERT.equals(5));

        Order editedCamembertOrder =
            new OrderBuilder(ORDER_CAMEMBERT).withOrderDate("2021-08-04")
                .withCheeseType("Brie").build();
        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));
    }
}
