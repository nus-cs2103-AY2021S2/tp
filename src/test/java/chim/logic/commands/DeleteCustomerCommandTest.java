package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.assertCommandFailure;
import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static chim.testutil.TypicalCheese.CAMEMBERT;
import static chim.testutil.TypicalCheese.CAMEMBERT_2;
import static chim.testutil.TypicalCheese.FETA;
import static chim.testutil.TypicalCustomers.ALICE;
import static chim.testutil.TypicalCustomers.BENSON;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static chim.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;
import static chim.testutil.TypicalIndexes.INDEX_TYPICAL_CUSTOMER;
import static chim.testutil.TypicalModels.getTypicalChim;
import static chim.testutil.TypicalOrder.ORDER_CAMEMBERT;
import static chim.testutil.TypicalOrder.ORDER_CAMEMBERT_2;
import static chim.testutil.TypicalOrder.ORDER_FETA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chim.commons.core.Messages;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;
import chim.model.customer.Customer;
import chim.model.customer.Phone;
import chim.model.util.predicate.FieldPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCustomerCommand}.
 */
public class DeleteCustomerCommandTest {

    private Model model = new ModelManager(getTypicalChim(), new UserPrefs());

    @Test
    public void execute_validPhoneUnfilteredList_success() {
        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_TYPICAL_CUSTOMER.getZeroBased());
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(customerToDelete.getPhone());

        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
                customerToDelete);

        ModelManager expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);
        expectedModel.setPanelToCustomerList();

        assertCommandSuccess(deleteCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPhoneUnfilteredList_throwsCommandException() {
        Phone invalidPhone = new Phone("000");
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(invalidPhone);

        assertCommandFailure(deleteCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_PHONE);
    }

    @Test
    public void execute_validPhoneFilteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(
                new Phone(customerToDelete.getPhone().value));

        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
                customerToDelete);

        Model expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);
        assertFalse(expectedModel.hasOrder(ORDER_CAMEMBERT));
        assertFalse(expectedModel.hasCheese(CAMEMBERT));
        expectedModel.setPanelToCustomerList();
        showNoCustomer(expectedModel);

        assertCommandSuccess(deleteCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPhoneFilteredListDoubleOrders_success() {
        Customer customerToDelete = model.getFilteredCustomerList().get(INDEX_SECOND_CUSTOMER.getZeroBased());
        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(
                new Phone(customerToDelete.getPhone().value));

        String expectedMessage = String.format(DeleteCustomerCommand.MESSAGE_DELETE_CUSTOMER_SUCCESS,
                customerToDelete);

        Model expectedModel = new ModelManager(model.getChim(), new UserPrefs());
        expectedModel.deleteCustomer(customerToDelete);
        assertFalse(expectedModel.hasOrder(ORDER_FETA));
        assertFalse(expectedModel.hasOrder(ORDER_CAMEMBERT_2));
        assertFalse(expectedModel.hasCheese(FETA));
        assertFalse(expectedModel.hasCheese(CAMEMBERT_2));
        expectedModel.setPanelToCustomerList();

        assertCommandSuccess(deleteCustomerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPhoneFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Phone invalidPhone = new Phone("000");

        DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(invalidPhone);

        assertCommandFailure(deleteCustomerCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_PHONE);
    }

    @Test
    public void equals() {
        DeleteCustomerCommand deleteFirstCommand = new DeleteCustomerCommand(ALICE.getPhone());
        DeleteCustomerCommand deleteSecondCommand = new DeleteCustomerCommand(BENSON.getPhone());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCustomerCommand deleteFirstCommandCopy = new DeleteCustomerCommand(ALICE.getPhone());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCustomer(Model model) {
        model.updateFilteredCustomerList(FieldPredicate.getEmptyPredicate());

        assertTrue(model.getFilteredCustomerList().isEmpty());
    }
}
