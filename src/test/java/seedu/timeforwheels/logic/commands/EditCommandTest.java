package seedu.timeforwheels.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_TAG_HEAVY;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;
import static seedu.timeforwheels.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.timeforwheels.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.commons.core.Messages;
import seedu.timeforwheels.commons.core.index.Index;
import seedu.timeforwheels.logic.commands.EditCommand.EditCustomerDescriptor;
import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.Model;
import seedu.timeforwheels.model.ModelManager;
import seedu.timeforwheels.model.UserPrefs;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.testutil.CustomerBuilder;
import seedu.timeforwheels.testutil.EditCustomerDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalDeliveryList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Customer editedCustomer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(editedCustomer).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CUSTOMER, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new DeliveryList(model.getDeliveryList()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), editedCustomer);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCustomer = Index.fromOneBased(model.getFilteredCustomerList().size());
        Customer lastCustomer = model.getFilteredCustomerList().get(indexLastCustomer.getZeroBased());

        CustomerBuilder customerInList = new CustomerBuilder(lastCustomer);
        Customer editedCustomer = customerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HEAVY).build();

        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HEAVY).build();
        EditCommand editCommand = new EditCommand(indexLastCustomer, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new DeliveryList(model.getDeliveryList()), new UserPrefs());
        expectedModel.setCustomer(lastCustomer, editedCustomer);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CUSTOMER, new EditCommand.EditCustomerDescriptor());
        Customer editedCustomer = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new DeliveryList(model.getDeliveryList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        Customer customerInFilteredList = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        Customer editedCustomer = new CustomerBuilder(customerInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CUSTOMER,
                new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer);

        Model expectedModel = new ModelManager(new DeliveryList(model.getDeliveryList()), new UserPrefs());
        expectedModel.setCustomer(model.getFilteredCustomerList().get(0), editedCustomer);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCustomerUnfilteredList_failure() {
        Customer firstCustomer = model.getFilteredCustomerList().get(INDEX_FIRST_CUSTOMER.getZeroBased());
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(firstCustomer).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_CUSTOMER, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_duplicateCustomerFilteredList_failure() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);

        // edit customer in filtered list into a duplicate in delivery list
        Customer customerInList = model.getDeliveryList().getCustomerList().get(INDEX_SECOND_CUSTOMER.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CUSTOMER,
                new EditCustomerDescriptorBuilder(customerInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CUSTOMER);
    }

    @Test
    public void execute_invalidCustomerIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCustomerList().size() + 1);
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of delivery list
     */
    @Test
    public void execute_invalidCustomerIndexFilteredList_failure() {
        showCustomerAtIndex(model, INDEX_FIRST_CUSTOMER);
        Index outOfBoundIndex = INDEX_SECOND_CUSTOMER;
        // ensures that outOfBoundIndex is still in bounds of delivery list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDeliveryList().getCustomerList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_CUSTOMER, DESC_AMY);

        // same values -> returns true
        EditCustomerDescriptor copyDescriptor = new EditCustomerDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_CUSTOMER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_CUSTOMER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_CUSTOMER, DESC_BOB)));
    }

}
