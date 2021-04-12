package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.testutil.TypicalOrderItems;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteOrderItemCommandTest {

    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
            TypicalOrderItems.getTypicalOrderItemsModel());
    @Test
    public void execute_validIndexUnfilteredList_success() {
        OrderItem orderItemToDeleteOne = model.getFilteredOrderItemsList().get(INDEX_FIRST_ORDER.getZeroBased());
        OrderItem orderItemToDeleteTwo = model.getFilteredOrderItemsList().get(INDEX_THIRD_ORDER.getZeroBased());
        ArrayList<Index> arrayOrderItems = new ArrayList<Index>();
        arrayOrderItems.add(INDEX_FIRST_ORDER);
        arrayOrderItems.add(INDEX_THIRD_ORDER);
        IndexList indexList = new IndexList(arrayOrderItems);
        indexList.sortList();
        DeleteOrderItemCommand deleteOrderItemCommand = new DeleteOrderItemCommand(indexList);

        String convertedToString = "";
        convertedToString = convertedToString + String.format("\n%1$s", orderItemToDeleteTwo);
        convertedToString = convertedToString + String.format("\n%1$s", orderItemToDeleteOne);

        String expectedMessage = String.format(DeleteOrderItemCommand.MESSAGE_DELETE_ORDER_ITEMS_SUCCESS,
                convertedToString);

        ModelManager expectedModel = new ModelManager(getTypicalCakeCollate(), new UserPrefs(),
                TypicalOrderItems.getTypicalOrderItemsModel());
        expectedModel.deleteOrderItem(orderItemToDeleteTwo);
        expectedModel.deleteOrderItem(orderItemToDeleteOne);

        assertCommandSuccess(deleteOrderItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderItemsList().size() + 1);
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(outOfBoundIndex);
        IndexList outOfBoundIndexList = new IndexList(array);
        DeleteOrderItemCommand deleteOrderItemCommand = new DeleteOrderItemCommand(outOfBoundIndexList);

        assertCommandFailure(deleteOrderItemCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> arrayFirstOrderItem = new ArrayList<Index>();
        arrayFirstOrderItem.add(INDEX_FIRST_ORDER);
        IndexList indexListFirstOrderItem = new IndexList(arrayFirstOrderItem);

        ArrayList<Index> arraySecondOrderItem = new ArrayList<Index>();
        arraySecondOrderItem.add(INDEX_SECOND_ORDER);
        IndexList indexListSecondOrderItem = new IndexList(arraySecondOrderItem);

        ArrayList<Index> arrayFirstAndSecondOrderItem = new ArrayList<Index>();
        arrayFirstAndSecondOrderItem.add(INDEX_FIRST_ORDER);
        arrayFirstAndSecondOrderItem.add(INDEX_SECOND_ORDER);
        IndexList indexListFirstAndSecondOrderItem = new IndexList(arrayFirstAndSecondOrderItem);
        indexListFirstAndSecondOrderItem.sortList();

        ArrayList<Index> arraySecondAndFirstOrderItem = new ArrayList<Index>();
        arraySecondAndFirstOrderItem.add(INDEX_SECOND_ORDER);
        arraySecondAndFirstOrderItem.add(INDEX_FIRST_ORDER);
        IndexList indexListSecondAndFirstOrderItem = new IndexList(arraySecondAndFirstOrderItem);
        indexListSecondAndFirstOrderItem.sortList();

        DeleteOrderItemCommand deleteFirstCommand = new DeleteOrderItemCommand(indexListFirstOrderItem);
        DeleteOrderItemCommand deleteSecondCommand = new DeleteOrderItemCommand(indexListSecondOrderItem);
        DeleteOrderItemCommand deleteFirstAndSecondOrder =
                new DeleteOrderItemCommand(indexListFirstAndSecondOrderItem);
        DeleteOrderItemCommand deleteSecondAndFirstOrder =
                new DeleteOrderItemCommand(indexListSecondAndFirstOrderItem);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(array);
        DeleteOrderItemCommand deleteFirstCommandCopy = new DeleteOrderItemCommand(indexList);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        assertTrue(deleteFirstAndSecondOrder.equals(deleteSecondAndFirstOrder));
        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

    }
}
