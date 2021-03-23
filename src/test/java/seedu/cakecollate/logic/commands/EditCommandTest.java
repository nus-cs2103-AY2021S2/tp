package seedu.cakecollate.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.cakecollate.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.cakecollate.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cakecollate.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cakecollate.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.cakecollate.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.cakecollate.testutil.TypicalOrders.getTypicalCakeCollate;

import org.junit.jupiter.api.Test;

import seedu.cakecollate.commons.core.Messages;
import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.logic.commands.EditCommand.EditOrderDescriptor;
import seedu.cakecollate.logic.commands.exceptions.CommandException;
import seedu.cakecollate.model.CakeCollate;
import seedu.cakecollate.model.Model;
import seedu.cakecollate.model.ModelManager;
import seedu.cakecollate.model.UserPrefs;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.testutil.EditOrderDescriptorBuilder;
import seedu.cakecollate.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalCakeCollate(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Order editedOrder = new OrderBuilder().build();
        EditCommand.EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(editedOrder).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ORDER, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new CakeCollate(model.getCakeCollate()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);
        System.out.println("1: " + expectedMessage);
        System.out.println("2: " + editCommand.execute(model).getFeedbackToUser());
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);
        Order editedOrder = orderInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastOrder, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new CakeCollate(model.getCakeCollate()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ORDER, new EditCommand.EditOrderDescriptor());
        Order editedOrder = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new CakeCollate(model.getCakeCollate()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ORDER,
                new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new CakeCollate(model.getCakeCollate()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateOrderUnfilteredList_failure() {
        Order firstOrder = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        EditCommand.EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(firstOrder).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ORDER, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_duplicateOrderFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        // edit order in filtered list into a duplicate in cakecollate
        Order orderInList = model.getCakeCollate().getOrderList().get(INDEX_SECOND_ORDER.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ORDER,
                new EditOrderDescriptorBuilder(orderInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of cakecollate
     */
    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);
        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of cakecollate list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCakeCollate().getOrderList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditOrderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ORDER, DESC_AMY);

        // same values -> returns true
        EditCommand.EditOrderDescriptor copyDescriptor = new EditOrderDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ORDER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ORDER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ORDER, DESC_BOB)));
    }

}
