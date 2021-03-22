package seedu.cakecollate.logic.commands;

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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.cakecollate.logic.commands.CommandTestUtil.*;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

class DeliveryStatusCommandTest {
    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Order orderToUpdate = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(indexList, new DeliveryStatus());

        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS,
                String.format("\n%1$s", orderToUpdate));

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs());
        expectedModel.setOrder(expectedModel.getFilteredOrderList().get(0), orderToUpdate);

        assertCommandSuccess(deliveryStatusCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(outOfBoundIndex);
        IndexList outOfBoundIndexList = new IndexList(array);
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(outOfBoundIndexList, new DeliveryStatus());

        assertCommandFailure(deliveryStatusCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Order orderToUpdate = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(indexList, new DeliveryStatus());

        String expectedMessage = String.format(DeliveryStatusCommand.MESSAGE_DELIVERY_STATUS_ORDER_SUCCESS,
                String.format("\n%1$s", orderToUpdate));

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs());
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
        DeliveryStatusCommand deliveryStatusCommand = new DeliveryStatusCommand(outOfBoundIndexList, new DeliveryStatus());

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
        DeliveryStatusCommand deliveryStatusFirstCommandCopy = new DeliveryStatusCommand(indexList, new DeliveryStatus());
        assertTrue(deliveryStatusFirstCommand.equals(deliveryStatusFirstCommandCopy));

        // different types -> returns false
        assertFalse(deliveryStatusFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deliveryStatusFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(deliveryStatusFirstCommand.equals(deliveryStatusSecondCommand));

    }
}