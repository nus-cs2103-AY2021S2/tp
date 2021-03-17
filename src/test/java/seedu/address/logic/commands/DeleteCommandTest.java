package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalOrders.getTypicalCakeCollate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.IndexList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        ArrayList<Index> arrayFirstOrder = new ArrayList<Index>();
        arrayFirstOrder.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(arrayFirstOrder);
        DeleteCommand deleteCommand = new DeleteCommand(indexList);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        ModelManager expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs());
        expectedModel.deleteOrder(orderToDelete);

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

        Model expectedModel = new ModelManager(model.getCakeCollate(), new UserPrefs());
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
        DeleteCommand deleteFirstCommand = new DeleteCommand(indexListFirstOrder);
        DeleteCommand deleteSecondCommand = new DeleteCommand(indexListSecondOrder);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ArrayList<Index> array = new ArrayList<Index>();
        array.add(INDEX_FIRST_ORDER);
        IndexList indexList = new IndexList(array);
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(indexList);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

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
