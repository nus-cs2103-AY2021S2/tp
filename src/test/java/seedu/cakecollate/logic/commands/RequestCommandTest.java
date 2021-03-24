package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_REQUEST_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_REQUEST_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.Request;
import seedu.cakecollate.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RequestCommand.
 */
public class RequestCommandTest {

    private static final String REQUEST_STUB = "Some request";

    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs());

    @Test
    public void execute_addRequestUnfilteredList_success() {
        Order firstOrder = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order editedOrder = new OrderBuilder(firstOrder).withRequest(REQUEST_STUB).build();

        RequestCommand requestCommand = new RequestCommand(INDEX_FIRST_ORDER,
                new Request(editedOrder.getRequest().toString()));

        String expectedMessage = String.format(RequestCommand.MESSAGE_ADD_REQUEST_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new CakeCollate(model.getCakeCollate()), new UserPrefs());
        expectedModel.setOrder(firstOrder, editedOrder);

        assertCommandSuccess(requestCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order firstOrder = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order editedOrder = new OrderBuilder(model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased()))
                .withRequest(REQUEST_STUB).build();

        RequestCommand requestCommand = new RequestCommand(INDEX_FIRST_ORDER,
                new Request(editedOrder.getRequest().value));

        String expectedMessage = String.format(RequestCommand.MESSAGE_ADD_REQUEST_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new CakeCollate(model.getCakeCollate()), new UserPrefs());
        expectedModel.setOrder(firstOrder, editedOrder);

        assertCommandSuccess(requestCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        RequestCommand requestCommand = new RequestCommand(outOfBoundIndex, new Request(VALID_REQUEST_BOB));

        assertCommandFailure(requestCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of CakeCollate
     */
    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);
        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of CakeCollate list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCakeCollate().getOrderList().size());

        RequestCommand requestCommand = new RequestCommand(outOfBoundIndex, new Request(VALID_REQUEST_BOB));

        assertCommandFailure(requestCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RequestCommand standardCommand = new RequestCommand(INDEX_FIRST_ORDER,
                new Request(VALID_REQUEST_AMY));

        // same values -> returns true
        RequestCommand commandWithSameValues = new RequestCommand(INDEX_FIRST_ORDER,
                new Request(VALID_REQUEST_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RequestCommand(INDEX_SECOND_ORDER,
                new Request(VALID_REQUEST_AMY))));

        // different Request -> returns false
        assertFalse(standardCommand.equals(new RequestCommand(INDEX_FIRST_ORDER,
                new Request(VALID_REQUEST_BOB))));
    }
}
