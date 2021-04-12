package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.ALICE;
import static seedu.cakecollate.testutil.TypicalOrders.BOB;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.DeliveryStatus;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.Status;
import seedu.cakecollate.testutil.OrderBuilder;
import seedu.cakecollate.testutil.TypicalOrderItems;

class DeliveryStatusCommandTest {
    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());

    @Test
    public void execute_validIndexUnfilteredListWithSameStatus_success() {
        Order orderToUpdate = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());

        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);

        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(indexList, orderToUpdate.getDeliveryStatus());

        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME,
                String.format("\n%1$s", orderToUpdate));

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs(), model.getOrderItems());
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(0), orderToUpdate);

        assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_validIndexUnfilteredListWithDifferentStatus_success() {
        Order orderToUpdate = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Status status;
        if (orderToUpdate.getDeliveryStatus().equals(new DeliveryStatus())) {
            status = Status.CANCELLED;
        } else {
            status = Status.UNDELIVERED;
        }
        Order updatedOrder = new OrderBuilder(orderToUpdate).withDeliveryStatus(status).build();

        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_UPDATED,
                String.format("\n%1$s", updatedOrder));

        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);

        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(indexList, new DeliveryStatus(status));

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs(), model.getOrderItems());
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(0), updatedOrder);

        assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesUnfilteredListWithSameStatus_success() {
        Order orderToUpdateOne = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order orderToUpdateTwo = model.getFilteredOrderList().get(INDEX_SECOND_ORDER.getZeroBased());

        if (!orderToUpdateOne.getDeliveryStatus().equals(orderToUpdateTwo.getDeliveryStatus())) {
            orderToUpdateTwo = new OrderBuilder(orderToUpdateTwo)
                    .withDeliveryStatus(orderToUpdateOne.getDeliveryStatus().getDeliveryStatus())
                    .build();
        }

        ArrayList<Index> indexArrayList = new ArrayList<Index>();
        indexArrayList.add(INDEX_FIRST_ORDER);
        indexArrayList.add(INDEX_SECOND_ORDER);
        IndexList indexList = new IndexList(indexArrayList);

        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(indexList, orderToUpdateOne.getDeliveryStatus());

        String result = String.format("\n%1$s", orderToUpdateOne) + String.format("\n%1$s", orderToUpdateTwo);
        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME,
                result);

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs(), model.getOrderItems());
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(0), orderToUpdateOne);
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(1), orderToUpdateTwo);

        assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_validIndexesUnfilteredListWithDifferentStatus_success() {
        Order order1 = new OrderBuilder(ALICE).withDeliveryStatus(Status.UNDELIVERED).build();
        Order order2 = new OrderBuilder(BOB).withDeliveryStatus(Status.DELIVERED).build();
        Order updatedOrder2 = new OrderBuilder(BOB).withDeliveryStatus(Status.UNDELIVERED).build();

        List<Order> list = new ArrayList<Order>();
        list.add(order1);
        list.add(order2);
        CakeCollate c = new CakeCollate();
        c.setOrders(list);
        Model modelWithTwoOrders = new ModelManager(c, new UserPrefs(), TypicalOrderItems.getTypicalOrderItemsModel());

        Model expectedModel = new ModelManager(
                modelWithTwoOrders.getCakeCollate(),
                new UserPrefs(),
                modelWithTwoOrders.getOrderItems());


        ArrayList<Index> indexArrayList = new ArrayList<Index>();
        indexArrayList.add(INDEX_FIRST_ORDER);
        indexArrayList.add(INDEX_SECOND_ORDER);
        IndexList indexList = new IndexList(indexArrayList);

        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(indexList, order1.getDeliveryStatus());


        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_UPDATED,
                String.format("\n%1$s", updatedOrder2));

        expectedMessage += "\n\n" + String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME,
                String.format("\n%1$s", order1));

        expectedModel.setOrder(order1, order1);
        expectedModel.setOrder(order2, updatedOrder2);

        assertCommandSuccess(deliveryStatusCommand, modelWithTwoOrders, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(outOfBoundIndex);
        IndexList outOfBoundIndexList = new IndexList(array);
        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(outOfBoundIndexList, new DeliveryStatus());

        assertCommandFailure(deliveryStatusCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Order orderToUpdate = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(indexList, new DeliveryStatus());

        String expectedMessage;
        if (orderToUpdate.getDeliveryStatus().equals(new DeliveryStatus())) {
            expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME,
                    String.format("\n%1$s", orderToUpdate));
        } else {
            expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_UPDATED,
                    String.format("\n%1$s", orderToUpdate));
        }

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs(), model.getOrderItems());
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(0), orderToUpdate);

        assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of CakeCollate list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCakeCollate().getOrderList().size());
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(outOfBoundIndex);
        IndexList outOfBoundIndexList = new IndexList(array);
        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(outOfBoundIndexList, new DeliveryStatus());

        assertCommandFailure(deliveryStatusCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexListFirstOrder = new IndexList(arrayFirstOrder);

        ArrayList<Index> arraySecondOrder = new ArrayList<Index>();
        arraySecondOrder.add(INDEX_SECOND_ORDER);
        IndexList indexListSecondOrder = new IndexList(arraySecondOrder);
        DeliveryStatusCommand deliveryStatusFirstCommand =
                new DeliveryStatusCommand(indexListFirstOrder, new DeliveryStatus());
        DeliveryStatusCommand deliveryStatusSecondCommand =
                new DeliveryStatusCommand(indexListSecondOrder, new DeliveryStatus());

        // same object -> returns true
        assertTrue(deliveryStatusFirstCommand.equals(deliveryStatusFirstCommand));

        // same values -> returns true
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(array);
        DeliveryStatusCommand deliveryStatusFirstCommandCopy =
                new DeliveryStatusCommand(indexList, new DeliveryStatus());
        assertTrue(deliveryStatusFirstCommand.equals(deliveryStatusFirstCommandCopy));

        // different types -> returns false
        assertFalse(deliveryStatusFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deliveryStatusFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(deliveryStatusFirstCommand.equals(deliveryStatusSecondCommand));

    }
}
