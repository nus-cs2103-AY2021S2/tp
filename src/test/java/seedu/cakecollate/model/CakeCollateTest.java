package seedu.cakecollate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrders.ALICE;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.exceptions.DuplicateOrderException;
import seedu.cakecollate.testutil.OrderBuilder;

public class CakeCollateTest {

    private final CakeCollate cakeCollate = new CakeCollate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), cakeCollate.getOrderList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> cakeCollate.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCakeCollate_replacesData() {
        CakeCollate newData = getTypicalCakeCollate();
        cakeCollate.resetData(newData);
        assertEquals(newData, cakeCollate);
    }

    @Test
    public void resetData_withDuplicateOrders_throwsDuplicateOrderException() {
        // Two orders with the same identity fields
        Order editedAlice = new OrderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Order> newOrders = Arrays.asList(ALICE, editedAlice);
        CakeCollateStub newData = new CakeCollateStub(newOrders);

        assertThrows(DuplicateOrderException.class, () -> cakeCollate.resetData(newData));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> cakeCollate.hasOrder(null));
    }

    @Test
    public void hasOrder_orderNotInCakeCollate_returnsFalse() {
        assertFalse(cakeCollate.hasOrder(ALICE));
    }

    @Test
    public void hasOrder_orderInCakeCollate_returnsTrue() {
        cakeCollate.addOrder(ALICE);
        assertTrue(cakeCollate.hasOrder(ALICE));
    }

    @Test
    public void hasOrder_orderWithSameIdentityFieldsInCakeCollate_returnsTrue() {
        cakeCollate.addOrder(ALICE);
        Order editedAlice = new OrderBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(cakeCollate.hasOrder(editedAlice));
    }

    @Test
    public void getOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> cakeCollate.getOrderList().remove(0));
    }

    /**
     * A stub ReadOnlyCakeCollate whose orders list can violate interface constraints.
     */
    private static class CakeCollateStub implements ReadOnlyCakeCollate {
        private final ObservableList<Order> orders = FXCollections.observableArrayList();

        CakeCollateStub(Collection<Order> orders) {
            this.orders.setAll(orders);
        }

        @Override
        public ObservableList<Order> getOrderList() {
            return orders;
        }
    }

}
