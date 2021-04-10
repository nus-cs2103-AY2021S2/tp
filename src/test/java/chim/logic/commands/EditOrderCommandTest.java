package chim.logic.commands;

import static chim.commons.core.Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX;
import static chim.logic.commands.CommandTestUtil.VALID_ORDER_DATE_1;
import static chim.logic.commands.CommandTestUtil.VALID_PHONE_CARL;
import static chim.logic.commands.CommandTestUtil.VALID_QUANTITY_5;
import static chim.logic.commands.CommandTestUtil.assertCommandFailure;
import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.logic.commands.CommandTestUtil.showOrderAtIndex;
import static chim.testutil.TypicalIndexes.INDEX_COMPLETED_ORDER;
import static chim.testutil.TypicalIndexes.INDEX_FILTERED_ORDER;
import static chim.testutil.TypicalIndexes.INDEX_LAST_ORDER;
import static chim.testutil.TypicalIndexes.INDEX_SECOND_UNCOMPLETED_ORDER;
import static chim.testutil.TypicalModels.getTypicalChim;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chim.commons.core.index.Index;
import chim.logic.commands.EditOrderCommand.EditOrderDescriptor;
import chim.model.Chim;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;
import chim.model.order.Order;
import chim.testutil.EditOrderDescriptorBuilder;
import chim.testutil.OrderBuilder;

public class EditOrderCommandTest {
    private Model model = new ModelManager(getTypicalChim(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Order order = model.getFilteredOrderList().get(INDEX_SECOND_UNCOMPLETED_ORDER.getZeroBased());
        assertFalse(order.isComplete());

        Order editedOrder = new OrderBuilder()
                .withOrderId(order.getOrderId().value)
                .withCustomerId(model.getFilteredOrderList().get(0).getCustomerId())
                .build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(editedOrder)
                .withPhone(VALID_PHONE_CARL)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(3), editedOrder);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        Order order = model.getFilteredOrderList().get(INDEX_SECOND_UNCOMPLETED_ORDER.getZeroBased());
        assertFalse(order.isComplete());

        Order editedOrder = new OrderBuilder(order)
                .withCustomerId(model.getFilteredOrderList().get(0).getCustomerId())
                .build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withPhone(VALID_PHONE_CARL)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(3), editedOrder);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moreThanOneFieldsSpecifiedUnfilteredList_success() {
        Order order = model.getFilteredOrderList().get(INDEX_SECOND_UNCOMPLETED_ORDER.getZeroBased());
        assertFalse(order.isComplete());

        Order editedOrder = new OrderBuilder(order)
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(3), editedOrder);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER,
                new EditOrderDescriptor());

        Order editedOrder = model.getFilteredOrderList().get(INDEX_SECOND_UNCOMPLETED_ORDER.getZeroBased());
        assertFalse(editedOrder.isComplete());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedFilteredList_success() {
        showOrderAtIndex(model, INDEX_SECOND_UNCOMPLETED_ORDER);

        Order order = model.getFilteredOrderList().get(INDEX_FILTERED_ORDER.getZeroBased());
        assertFalse(order.isComplete());

        Order editedOrder = new OrderBuilder(order)
                .withQuantity(VALID_QUANTITY_5)
                .build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FILTERED_ORDER, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moreThanOneFieldsSpecifiedFilteredList_success() {
        showOrderAtIndex(model, INDEX_SECOND_UNCOMPLETED_ORDER);

        Order order = model.getFilteredOrderList().get(INDEX_FILTERED_ORDER.getZeroBased());
        assertFalse(order.isComplete());

        Order editedOrder = new OrderBuilder(order)
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FILTERED_ORDER, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new Chim(model.getChim()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);
        expectedModel.setPanelToOrderList();

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_completedOrderUnfilteredList_failure() {
        Order order = model.getFilteredOrderList().get(INDEX_COMPLETED_ORDER.getZeroBased());
        assertTrue(order.isComplete());

        // Empty descriptor
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_COMPLETED_ORDER, new EditOrderDescriptor());
        assertCommandFailure(editOrderCommand, model, EditOrderCommand.MESSAGE_COMPLETED_ORDER_ERROR);

        // non-empty descriptor
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        editOrderCommand = new EditOrderCommand(INDEX_COMPLETED_ORDER, descriptor);
        assertCommandFailure(editOrderCommand, model, EditOrderCommand.MESSAGE_COMPLETED_ORDER_ERROR);
    }

    @Test
    public void execute_completedOrderFilteredList_failure() {
        showOrderAtIndex(model, INDEX_COMPLETED_ORDER);

        Order order = model.getFilteredOrderList().get(INDEX_FILTERED_ORDER.getZeroBased());
        assertTrue(order.isComplete());

        // Empty descriptor
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FILTERED_ORDER, new EditOrderDescriptor());
        assertCommandFailure(editOrderCommand, model, EditOrderCommand.MESSAGE_COMPLETED_ORDER_ERROR);

        // non-empty descriptor
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        editOrderCommand = new EditOrderCommand(INDEX_FILTERED_ORDER, descriptor);
        assertCommandFailure(editOrderCommand, model, EditOrderCommand.MESSAGE_COMPLETED_ORDER_ERROR);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editOrderCommand, model, MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_SECOND_UNCOMPLETED_ORDER);
        Index outOfBoundIndex = INDEX_LAST_ORDER;

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);
        assertCommandFailure(editOrderCommand, model, MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_5)
                .withOrderDate(VALID_ORDER_DATE_1)
                .build();

        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER, descriptor);

        // same values -> return true
        EditOrderDescriptor copy = new EditOrderDescriptor(descriptor);
        EditOrderCommand commandCopy = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER, copy);
        assertTrue(standardCommand.equals(commandCopy));

        // same object -> return true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(7));

        // different index -> returns false
        commandCopy = new EditOrderCommand(INDEX_LAST_ORDER, copy);
        assertFalse(standardCommand.equals(commandCopy));

        // different descriptor -> returns false
        commandCopy = new EditOrderCommand(INDEX_SECOND_UNCOMPLETED_ORDER, new EditOrderDescriptor());
        assertFalse(standardCommand.equals(commandCopy));
    }
}
