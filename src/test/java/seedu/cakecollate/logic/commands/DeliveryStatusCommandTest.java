package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
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
        Order orderToUpdateOne = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order orderToUpdateTwo = model.getFilteredOrderList().get(INDEX_SECOND_ORDER.getZeroBased());

        Status status;
        if (orderToUpdateOne.getDeliveryStatus().equals(orderToUpdateTwo.getDeliveryStatus())) {
            if (orderToUpdateOne.getDeliveryStatus().equals(new DeliveryStatus())) {
                status = Status.DELIVERED;
            } else {
                status = Status.UNDELIVERED;
            }
            orderToUpdateTwo = new OrderBuilder(orderToUpdateTwo).withDeliveryStatus(status).build();
        }

        ArrayList<Index> indexArrayList = new ArrayList<Index>();
        indexArrayList.add(INDEX_FIRST_ORDER);
        indexArrayList.add(INDEX_SECOND_ORDER);
        IndexList indexList = new IndexList(indexArrayList);

        DeliveryStatusCommand deliveryStatusCommand =
                new DeliveryStatusCommand(indexList, orderToUpdateOne.getDeliveryStatus());

        Order updatedOrderTwo = new OrderBuilder(orderToUpdateTwo)
                .withDeliveryStatus(orderToUpdateOne.getDeliveryStatus().getDeliveryStatus())
                .build();

        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_UPDATED,
                String.format("\n%1$s", updatedOrderTwo));

        expectedMessage += "\n\n" + String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS_SAME,
                String.format("\n%1$s", orderToUpdateOne));

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs(), model.getOrderItems());
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(0), orderToUpdateOne);
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(1), updatedOrderTwo);

        model.setOrder(expectedModel.getFilteredOrderList().get(1), orderToUpdateTwo);

        assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel);

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
