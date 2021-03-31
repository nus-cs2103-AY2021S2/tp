package seedu.cakecollate.model.orderitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_COST_STRAWBERRY;
import static seedu.cakecollate.testutil.Assert.assertThrows;
import static seedu.cakecollate.testutil.TypicalOrderItems.CHOCOLATE;
import static seedu.cakecollate.testutil.TypicalOrderItems.STRAWBERRY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.model.orderitem.exceptions.DuplicateOrderItemException;
import seedu.cakecollate.model.orderitem.exceptions.OrderItemNotFoundException;
import seedu.cakecollate.testutil.OrderItemBuilder;

public class UniqueOrderItemListTest {

    private final UniqueOrderItemList uniqueOrderItemList = new UniqueOrderItemList();

    @Test
    public void contains_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.contains(null));
    }

    @Test
    public void contains_orderItemNotInList_returnsFalse() {
        assertFalse(uniqueOrderItemList.contains(CHOCOLATE));
    }

    @Test
    public void contains_orderItemInList_returnsTrue() {
        uniqueOrderItemList.add(CHOCOLATE);
        assertTrue(uniqueOrderItemList.contains(CHOCOLATE));
    }

    @Test
    public void contains_orderItemWithSameTypeInList_returnsTrue() {
        uniqueOrderItemList.add(CHOCOLATE);
        OrderItem editedChocolate = new OrderItemBuilder(CHOCOLATE).withCost(VALID_COST_STRAWBERRY).build();
        assertTrue(uniqueOrderItemList.contains(editedChocolate));
    }

    @Test
    public void add_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.add(null));
    }

    @Test
    public void add_duplicateOrderItem_throwsDuplicateOrderItemException() {
        uniqueOrderItemList.add(CHOCOLATE);
        assertThrows(DuplicateOrderItemException.class, () -> uniqueOrderItemList.add(CHOCOLATE));
    }

    @Test
    public void setOrderItem_nullTargetOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.setOrderItem(null, CHOCOLATE));
    }

    @Test
    public void setOrderItem_nullEditedOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.setOrderItem(CHOCOLATE, null));
    }

    @Test
    public void setOrderItem_targetOrderItemNotInList_throwsOrderItemNotFoundException() {
        assertThrows(OrderItemNotFoundException.class, () -> uniqueOrderItemList.setOrderItem(CHOCOLATE, CHOCOLATE));
    }

    @Test
    public void setOrderItem_editedOrderItemIsSameOrder_success() {
        uniqueOrderItemList.add(CHOCOLATE);
        uniqueOrderItemList.setOrderItem(CHOCOLATE, CHOCOLATE);
        UniqueOrderItemList expectedUniqueOrderItemList = new UniqueOrderItemList();
        expectedUniqueOrderItemList.add(CHOCOLATE);
        assertEquals(expectedUniqueOrderItemList, uniqueOrderItemList);
    }

    @Test
    public void setOrderItem_editedOrderItemHasSameType_success() {
        uniqueOrderItemList.add(CHOCOLATE);
        OrderItem editedChocolate = new OrderItemBuilder(CHOCOLATE).withCost(VALID_COST_STRAWBERRY).build();
        uniqueOrderItemList.setOrderItem(CHOCOLATE, editedChocolate);
        UniqueOrderItemList expectedUniqueOrderItemList = new UniqueOrderItemList();
        expectedUniqueOrderItemList.add(editedChocolate);
        assertEquals(expectedUniqueOrderItemList, uniqueOrderItemList);
    }

    @Test
    public void setOrderItem_editedOrderItemHasDifferentType_success() {
        uniqueOrderItemList.add(CHOCOLATE);
        uniqueOrderItemList.setOrderItem(CHOCOLATE, STRAWBERRY);
        UniqueOrderItemList expectedUniqueOrderList = new UniqueOrderItemList();
        expectedUniqueOrderList.add(STRAWBERRY);
        assertEquals(expectedUniqueOrderList, uniqueOrderItemList);
    }

    @Test
    public void setOrderItem_editedOrderItemHasNonUniqueType_throwsDuplicateOrderItemException() {
        uniqueOrderItemList.add(CHOCOLATE);
        uniqueOrderItemList.add(STRAWBERRY);
        assertThrows(DuplicateOrderItemException.class, () -> uniqueOrderItemList.setOrderItem(CHOCOLATE, STRAWBERRY));
    }

    @Test
    public void remove_nullOrderItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.remove(null));
    }

    @Test
    public void remove_orderItemDoesNotExist_throwsOrderItemNotFoundException() {
        assertThrows(OrderItemNotFoundException.class, () -> uniqueOrderItemList.remove(CHOCOLATE));
    }

    @Test
    public void remove_existingOrderItem_removesOrderItem() {
        uniqueOrderItemList.add(CHOCOLATE);
        uniqueOrderItemList.remove(CHOCOLATE);
        UniqueOrderItemList expectedUniqueOrderList = new UniqueOrderItemList();
        assertEquals(expectedUniqueOrderList, uniqueOrderItemList);
    }

    @Test
    public void setOrderItems_nullUniqueOrderItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.setOrderItems((UniqueOrderItemList) null));
    }

    @Test
    public void setOrderItems_uniqueOrderItemList_replacesOwnListWithProvidedUniqueOrderItemList() {
        uniqueOrderItemList.add(CHOCOLATE);
        UniqueOrderItemList expectedUniqueOrderItemList = new UniqueOrderItemList();
        expectedUniqueOrderItemList.add(STRAWBERRY);
        uniqueOrderItemList.setOrderItems(expectedUniqueOrderItemList);
        assertEquals(expectedUniqueOrderItemList, uniqueOrderItemList);
    }

    @Test
    public void setOrderItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderItemList.setOrderItems((List<OrderItem>) null));
    }

    @Test
    public void setOrderItems_list_replacesOwnListWithProvidedList() {
        uniqueOrderItemList.add(CHOCOLATE);
        List<OrderItem> orderItemList = Collections.singletonList(STRAWBERRY);
        uniqueOrderItemList.setOrderItems(orderItemList);
        UniqueOrderItemList expectedUniqueOrderList = new UniqueOrderItemList();
        expectedUniqueOrderList.add(STRAWBERRY);
        assertEquals(expectedUniqueOrderList, uniqueOrderItemList);
    }

    @Test
    public void setOrderItems_listWithDuplicateOrderItems_throwsDuplicateOrderItemException() {
        List<OrderItem> listWithDuplicateOrderItems = Arrays.asList(CHOCOLATE, CHOCOLATE);
        assertThrows(DuplicateOrderItemException.class, ()
            -> uniqueOrderItemList.setOrderItems(listWithDuplicateOrderItems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueOrderItemList.asUnmodifiableObservableList().remove(0));
    }
}
