package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.assertCommandFailure;
import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.logic.commands.CommandTestUtil.showOrderAtIndex;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static chim.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static chim.testutil.TypicalModels.getTypicalChim;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chim.commons.core.Messages;
import chim.commons.core.index.Index;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;
import chim.model.customer.Name;
import chim.model.order.Order;
import chim.model.util.predicate.FieldPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteOrderCommand}.
 */
public class DeleteOrderCommandTest {
    private Model model = new ModelManager(getTypicalChim(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER);

        Name name = model.getCustomerWithId(orderToDelete.getCustomerId()).getName();
        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS,
                orderToDelete, name);

        ModelManager expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.deleteOrder(orderToDelete);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(deleteOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER);
        Name name = model.getCustomerWithId(orderToDelete.getCustomerId()).getName();

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS,
                orderToDelete, name);

        Model expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.deleteOrder(orderToDelete);
        showNoOrder(expectedModel);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(deleteOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of CHIM's full order list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getChim().getOrderList().size());

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteOrderCommand deleteFirstCommand = new DeleteOrderCommand(INDEX_FIRST_ORDER);
        DeleteOrderCommand deleteSecondCommand = new DeleteOrderCommand(INDEX_SECOND_ORDER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOrderCommand deleteFirstCommandCopy = new DeleteOrderCommand(INDEX_FIRST_ORDER);
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
        model.updateFilteredOrderList(FieldPredicate.getEmptyPredicate());

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
