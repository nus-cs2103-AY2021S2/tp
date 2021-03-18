package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.testutil.CustomerBuilder;

public class AddCustomerCommandTest {

    @Test
    public void constructor_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCustomerCommand(null));
    }

    @Test
    public void execute_customerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCustomerAdded modelStub = new ModelStubAcceptingCustomerAdded();
        Customer validCustomer = new CustomerBuilder().build();

        CommandResult commandResult = new AddCustomerCommand(validCustomer).execute(modelStub);

        assertEquals(String.format(AddCustomerCommand.MESSAGE_SUCCESS, validCustomer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCustomer), modelStub.customersAdded);
        assertTrue(modelStub.getGuiSettings().isShowCustomerListPanel());
    }

    @Test
    public void execute_duplicateCustomer_throwsCommandException() {
        Customer validCustomer = new CustomerBuilder().build();
        AddCustomerCommand addCommand = new AddCustomerCommand(validCustomer);
        ModelStub modelStub = new ModelStubWithCustomer(validCustomer);

        assertThrows(CommandException.class, AddCustomerCommand.MESSAGE_DUPLICATE_CUSTOMER, () -> addCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Customer alice = new CustomerBuilder().withName("Alice").build();
        Customer bob = new CustomerBuilder().withName("Bob").build();
        AddCustomerCommand addAliceCommand = new AddCustomerCommand(alice);
        AddCustomerCommand addBobCommand = new AddCustomerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCustomerCommand addAliceCommandCopy = new AddCustomerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different customer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single customer.
     */
    private class ModelStubWithCustomer extends ModelStub {
        private final Customer customer;

        ModelStubWithCustomer(Customer customer) {
            requireNonNull(customer);
            this.customer = customer;
        }

        @Override
        public boolean hasCustomer(Customer customer) {
            requireNonNull(customer);
            return this.customer.isSameCustomer(customer);
        }
    }

    /**
     * A Model stub that always accept the customer being added.
     */
    private class ModelStubAcceptingCustomerAdded extends ModelStub {
        final ArrayList<Customer> customersAdded = new ArrayList<>();
        private final ReadOnlyUserPrefs userPrefs = new UserPrefs();

        @Override
        public boolean hasCustomer(Customer customer) {
            requireNonNull(customer);
            return customersAdded.stream().anyMatch(customer::isSameCustomer);
        }

        @Override
        public void addCustomer(Customer customer) {
            requireNonNull(customer);
            customersAdded.add(customer);
        }

        @Override
        public GuiSettings getGuiSettings() {
            return userPrefs.getGuiSettings();
        }

        @Override
        public void setPanelToCustomerList() {
            getGuiSettings().setPanelToCustomerList();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
