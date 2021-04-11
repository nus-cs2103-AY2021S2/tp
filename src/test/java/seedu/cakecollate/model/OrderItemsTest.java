package seedu.cakecollate.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE;
import static seedu.cakecollate.testutil.TypicalOrderItems.getTypicalOrderItemsModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.exceptions.DuplicateOrderItemException;
import seedu.cakecollate.testutil.OrderItemBuilder;
import seedu.cakecollate.testutil.TypicalOrderItems;

public class OrderItemsTest {

    private final OrderItems orderItems = new OrderItems();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), orderItems.getOrderItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderItems.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyOrderItems_replacesData() {
        OrderItems newData = getTypicalOrderItemsModel();
        orderItems.resetData(newData);
        assertEquals(newData, orderItems);
    }

    @Test
    public void resetData_withDuplicateOrderItems_throwsDuplicateOrderItemsException() {
        OrderItem editedChocolate = new OrderItemBuilder(CHOCOLATE).build();
        List<OrderItem> newOrderItems = Arrays.asList(CHOCOLATE, editedChocolate);
        seedu.cakecollate.model.OrderItemsTest.OrderItemsStub newData =
                new seedu.cakecollate.model.OrderItemsTest.OrderItemsStub(newOrderItems);

        assertThrows(DuplicateOrderItemException.class, () -> orderItems.resetData(newData));
    }

    @Test
    public void hasOrderItem_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderItems.hasOrderItem(null));
    }

    @Test
    public void hasOrderItem_orderNotInOrderItems_returnsFalse() {
        assertFalse(orderItems.hasOrderItem(TypicalOrderItems.CHOCOLATE));
    }

    @Test
    public void hasOrderItem_orderItemInOrderItems_returnsTrue() {
        orderItems.addOrderItem(TypicalOrderItems.CHOCOLATE);
        assertTrue(orderItems.hasOrderItem(CHOCOLATE));
    }

    @Test
    public void hasOrderItem_orderItemWithDifferentTypeFieldInOrderItemsList_returnsFalse() {
        orderItems.addOrderItem(CHOCOLATE);
        OrderItem editedChocolate = new OrderItemBuilder(CHOCOLATE).withType("Strawberry")
                .build();
        assertFalse(orderItems.hasOrderItem(editedChocolate));
    }

    @Test
    public void getOrderItemsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> orderItems.getOrderItemList().remove(0));
    }

    /**
     * A stub OrderItemsStub whose order items list can violate interface constraints.
     */
    private static class OrderItemsStub implements ReadOnlyOrderItems {
        private final ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

        OrderItemsStub(Collection<OrderItem> orderItems) {
            this.orderItems.setAll(orderItems);
        }

        @Override
        public ObservableList<OrderItem> getOrderItemList() {
            return orderItems;
        }
    }

}
