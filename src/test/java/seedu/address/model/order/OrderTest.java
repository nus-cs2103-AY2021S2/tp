package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrder.ORDER_CAMEMBERT;
import static seedu.address.testutil.TypicalOrder.ORDER_FETA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderBuilder().withOrderDate(null).build());
    }

    @Test
    public void isSameOrder() {
        // same object -> return true
        assertTrue(ORDER_CAMEMBERT.isSameOrder(ORDER_CAMEMBERT));

        // null -> returns false
        assertFalse(ORDER_CAMEMBERT.isSameOrder(null));

        // same id, all other attributes different -> returns true
        Order editedOrder =
            new OrderBuilder(ORDER_CAMEMBERT).withCheeseType(VALID_CHEESE_TYPE_BRIE)
                .withOrderDate(VALID_ORDER_DATE_2)
                .withCompletedDate(VALID_COMPLETED_DATE_2)
                .build();
        assertTrue(ORDER_CAMEMBERT.isSameOrder(editedOrder));

        // different id, all other attributes same -> returns false
        Order newSimilarOrder =
            new OrderBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withOrderDate(VALID_ORDER_DATE_1)
                .withCompletedDate(VALID_COMPLETED_DATE_1)
                .build();
        assertFalse(ORDER_CAMEMBERT.isSameOrder(newSimilarOrder));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order camembertOrderCopy = new OrderBuilder(ORDER_CAMEMBERT).build();
        assertTrue(ORDER_CAMEMBERT.equals(camembertOrderCopy));

        // same object -> returns true
        assertTrue(ORDER_CAMEMBERT.equals(ORDER_CAMEMBERT));

        // null -> returns false
        assertFalse(ORDER_CAMEMBERT.equals(null));

        // different type -> returns false
        assertFalse(ORDER_CAMEMBERT.equals(5));

        // different cheese -> returns false
        assertFalse(ORDER_CAMEMBERT.equals(ORDER_FETA));

        // different order date -> returns false
        Order editedCamembertOrder =
            new OrderBuilder(ORDER_CAMEMBERT)
                .withOrderDate(VALID_ORDER_DATE_2)
                .build();
        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));
    }
}
