package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_THIRD_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.testutil.TypicalOrderItems;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Order orderToDeleteOne = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order orderToDeleteTwo = model.getFilteredOrderList().get(INDEX_THIRD_ORDER.getZeroBased());
        ArrayList<Index> arrayOrderItems = new ArrayList<Index>();
        arrayOrderItems.add(INDEX_FIRST_ORDER);
        arrayOrderItems.add(INDEX_THIRD_ORDER);
        IndexList indexList = new IndexList(arrayOrderItems);
        indexList.sortList();
        DeleteCommand deleteCommand = new DeleteCommand(indexList);

        String convertedToString = "";
        convertedToString = convertedToString + String.format("\n%1$s", orderToDeleteTwo);
        convertedToString = convertedToString + String.format("\n%1$s", orderToDeleteOne);


        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ORDERS_SUCCESS, convertedToString);

        ModelManager expectedModel = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
        expectedModel.deleteOrder(orderToDeleteTwo);
        expectedModel.deleteOrder(orderToDeleteOne);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(outOfBoundIndex);
        IndexList outOfBoundIndexList = new IndexList(array);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndexList);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        DeleteCommand deleteCommand = new DeleteCommand(indexList);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        Model expectedModel = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
        expectedModel.deleteOrder(orderToDelete);
        showNoOrder(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
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
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndexList);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexListFirstOrder = new IndexList(arrayFirstOrder);

        ArrayList<Index> arraySecondOrder = new ArrayList<Index>();
        arraySecondOrder.add(INDEX_SECOND_ORDER);
        IndexList indexListSecondOrder = new IndexList(arraySecondOrder);

        ArrayList<Index> arrayFirstAndSecondOrder = new ArrayList<Index>();
        arrayFirstAndSecondOrder.add(INDEX_FIRST_ORDER);
        arrayFirstAndSecondOrder.add(INDEX_SECOND_ORDER);
        IndexList indexListFirstAndSecondOrder = new IndexList(arrayFirstAndSecondOrder);
        indexListFirstAndSecondOrder.sortList();

        ArrayList<Index> arraySecondAndFirstOrder = new ArrayList<Index>();
        arraySecondAndFirstOrder.add(INDEX_SECOND_ORDER);
        arraySecondAndFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexListSecondAndFirstOrder = new IndexList(arraySecondAndFirstOrder);
        indexListSecondAndFirstOrder.sortList();

        DeleteCommand deleteFirstCommand = new DeleteCommand(indexListFirstOrder);
        DeleteCommand deleteSecondCommand = new DeleteCommand(indexListSecondOrder);
        DeleteCommand deleteFirstAndSecondOrder = new DeleteCommand(indexListFirstAndSecondOrder);
        DeleteCommand deleteSecondAndFirstOrder = new DeleteCommand(indexListSecondAndFirstOrder);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(array);
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(indexList);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        assertTrue(deleteFirstAndSecondOrder.equals(deleteSecondAndFirstOrder));
        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
