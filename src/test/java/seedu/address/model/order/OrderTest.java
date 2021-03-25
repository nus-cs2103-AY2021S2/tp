package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_BRIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHEESE_TYPE_CAMEMBERT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETED_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ORDER_DATE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCheese.CAMEMBERT;
import static seedu.address.testutil.TypicalCheese.FETA;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalOrder.ORDER_BRIE;
import static seedu.address.testutil.TypicalOrder.ORDER_CAMEMBERT;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderBuilder().withOrderDate(null).build());
    }

    @Test
    public void constructor_incompleteOrderNonEmptyCheeseSet_throwsIllegalArgumentException() {
        OrderBuilder orderBuilder = new OrderBuilder().withCheeses(Set.of(FETA.getCheeseId()));
        assertThrows(IllegalArgumentException.class,
            "The cheese set for an incomplete order should be empty.", () -> orderBuilder.build());
    }

    @Test
    public void constructor_completedDateBeforeOrderDate_throwsIllegalArgumentException() {
        OrderBuilder orderBuilder = new OrderBuilder().withCompletedDate(INVALID_COMPLETED_DATE_1)
                                            .withOrderDate(VALID_ORDER_DATE_3);
        assertThrows(IllegalArgumentException.class,
            "The completed date of an order should be after the order date.", () -> orderBuilder.build());
    }

    @Test
    public void constructor_completedOrderInsufficientCheese_throwsIllegalArgumentException() {
        OrderBuilder orderBuilder = new OrderBuilder().withCompletedDate(VALID_COMPLETED_DATE_1);
        assertThrows(IllegalArgumentException.class,
            "The number of cheeses in the order does not match the specified quantity.", () -> orderBuilder.build());
    }

    @Test
    public void idAutoIncrement() {
        final int orderId = 10;
        Order order = new OrderBuilder().withOrderId(orderId).build();
        Order order2 = new OrderBuilder().build();
        assertTrue(order.getOrderId().compareTo(order2.getOrderId()) < 0);
    }

    @Test
    public void isComplete() {
        assertTrue(ORDER_CAMEMBERT.isComplete());
        assertFalse(ORDER_BRIE.isComplete());
    }

    @Test
    public void isSameOrder() {
        // Same object -> return true
        assertTrue(ORDER_CAMEMBERT.isSameOrder(ORDER_CAMEMBERT));

        // Null -> returns false
        assertFalse(ORDER_CAMEMBERT.isSameOrder(null));

        // Same OrderId, Same customerId, all other attributes different -> returns true
        Order editedOrder =
            new OrderBuilder(ORDER_CAMEMBERT).withCheeseType(VALID_CHEESE_TYPE_BRIE)
                .withOrderId(ORDER_CAMEMBERT.getOrderId())
                .withQuantity(VALID_QUANTITY_1)
                .withOrderDate(VALID_ORDER_DATE_2)
                .withCompletedDate(VALID_COMPLETED_DATE_2)
                .withCustomerId(ALICE.getId())
                .withCheeses(Set.of(CAMEMBERT.getCheeseId()))
                .build();
        assertTrue(ORDER_CAMEMBERT.isSameOrder(editedOrder));

        // Different orderId, Same customerId, all other attributes same -> returns false
        Order newSimilarOrder =
            new OrderBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                .withQuantity(VALID_QUANTITY_1)
                .withOrderDate(VALID_ORDER_DATE_1)
                .withCompletedDate(VALID_COMPLETED_DATE_1)
                .withCustomerId(ALICE.getId())
                .withCheeses(Set.of(CAMEMBERT.getCheeseId()))
                .build();
        assertFalse(ORDER_CAMEMBERT.isSameOrder(newSimilarOrder));

        // Same Orderid, Different customerId, all other attributes same -> returns false
        newSimilarOrder =
                new OrderBuilder().withCheeseType(VALID_CHEESE_TYPE_CAMEMBERT)
                        .withOrderId(ORDER_CAMEMBERT.getOrderId())
                        .withQuantity(VALID_QUANTITY_1)
                        .withOrderDate(VALID_ORDER_DATE_1)
                        .withCompletedDate(VALID_COMPLETED_DATE_1)
                        .withCustomerId(BENSON.getId())
                        .withCheeses(Set.of(CAMEMBERT.getCheeseId()))
                        .build();
        assertFalse(ORDER_CAMEMBERT.isSameOrder(newSimilarOrder));
    }

    @Test
    public void equals() {
        // Same values -> returns true
        Order camembertOrderCopy = new OrderBuilder(ORDER_CAMEMBERT).build();
        assertTrue(ORDER_CAMEMBERT.equals(camembertOrderCopy));

        // Same object -> returns true
        assertTrue(ORDER_CAMEMBERT.equals(ORDER_CAMEMBERT));

        // Null -> returns false
        assertFalse(ORDER_CAMEMBERT.equals(null));

        // Different type -> returns false
        assertFalse(ORDER_CAMEMBERT.equals(5));

        // Different OrderId --> returns false
        final int orderId = 99;
        Order editedCamembertOrder = new OrderBuilder(ORDER_CAMEMBERT)
                .withOrderId(orderId)
                .build();

        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));

        // Different customerId --> returns false
        editedCamembertOrder = new OrderBuilder(ORDER_CAMEMBERT)
                .withCustomerId(BENSON.getId())
                .build();
        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));

        // Different cheese type -> returns false
        editedCamembertOrder = new OrderBuilder(ORDER_CAMEMBERT)
                .withCheeseType(VALID_CHEESE_TYPE_BRIE)
                .build();
        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));

        // Different order date -> returns false
        editedCamembertOrder =
            new OrderBuilder(ORDER_CAMEMBERT)
                .withOrderDate(VALID_ORDER_DATE_2)
                .build();
        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));

        // Different completed date -> returns false
        editedCamembertOrder =
                new OrderBuilder(ORDER_CAMEMBERT)
                        .withCompletedDate(VALID_COMPLETED_DATE_2)
                        .build();
        assertFalse(ORDER_CAMEMBERT.equals(editedCamembertOrder));

        // TODO: Add test for different cheese IDs
    }
}
