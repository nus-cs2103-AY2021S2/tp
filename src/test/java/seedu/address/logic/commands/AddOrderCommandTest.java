package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.GuiSettings.PanelToShow;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.OrderBuilder;

public class AddOrderCommandTest {

    @Test
    public void constructor_nullFields_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null, null, null, null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        // Instantiate a model stub with a single customer present.
        Customer validCustomer = new CustomerBuilder().build();
        ModelStubAcceptingOrderAddedWithCustomer modelStub = new
            ModelStubAcceptingOrderAddedWithCustomer(validCustomer);

        // Instantiate an AddOrderCommand with default fields in OrderBuilder and same phone
        Order validOrder = new OrderBuilder().build();
        AddOrderCommand addOrderCommand = new AddOrderCommand(validOrder.getCheeseType(), validCustomer.getPhone(),
                validOrder.getQuantity(), validOrder.getOrderDate());

        CommandResult commandResult = addOrderCommand.execute(modelStub);

        Order addedOrder = modelStub.getOrderAdded();
        assertTrue(addedOrder != null);
        assertEquals(String.format(AddOrderCommand.MESSAGE_SUCCESS, addedOrder),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.ordersAdded.size() == 1);
        assertEquals(modelStub.getGuiSettings().getPanel(), PanelToShow.ORDER_LIST);
    }

    @Test
    public void execute_customerPhoneNotFound_throwsCommandException() {
        Customer validCustomer = new CustomerBuilder().build();
        ModelStubAcceptingOrderAddedWithCustomer modelStub = new
            ModelStubAcceptingOrderAddedWithCustomer(validCustomer);

        Order validOrder = new OrderBuilder().build();
        AddOrderCommand addOrderCommand = new AddOrderCommand(validOrder.getCheeseType(), new Phone("65889999"),
                validOrder.getQuantity(), validOrder.getOrderDate());

        assertThrows(CommandException.class, AddOrderCommand.MESSAGE_NO_CUSTOMERS_FOUND, () -> addOrderCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Phone phone = new CustomerBuilder().build().getPhone();
        Order order1 = new OrderBuilder().withOrderDate("2020-12-30").build();
        Order order2 = new OrderBuilder().withOrderDate("2020-12-31").build();
        AddOrderCommand addOrderCommand1 = new AddOrderCommand(order1.getCheeseType(), phone,
                order1.getQuantity(), order1.getOrderDate());
        AddOrderCommand addOrderCommand2 = new AddOrderCommand(order2.getCheeseType(), phone,
                order2.getQuantity(), order2.getOrderDate());

        // same object -> returns true
        assertTrue(addOrderCommand1.equals(addOrderCommand1));

        // same values -> returns true
        AddOrderCommand addOrderCommand1Copy = new AddOrderCommand(order1.getCheeseType(),
                phone, order1.getQuantity(), order1.getOrderDate());
        assertTrue(addOrderCommand1.equals(addOrderCommand1Copy));

        // different types -> returns false
        assertFalse(addOrderCommand1.equals(1));

        // null -> returns false
        assertFalse(addOrderCommand1.equals(null));

        // different customer -> returns false
        assertFalse(addOrderCommand1.equals(addOrderCommand2));
    }


    /**
     * A Model stub that accepts the order being added referencing existing customer.
     */
    private class ModelStubAcceptingOrderAddedWithCustomer extends ModelStub {
        final Customer singleCustomer;
        final ArrayList<Order> ordersAdded = new ArrayList<>();
        private final ReadOnlyUserPrefs userPrefs = new UserPrefs();

        public ModelStubAcceptingOrderAddedWithCustomer(Customer validCustomer) {
            requireNonNull(validCustomer);
            singleCustomer = validCustomer;
        }

        private Order getOrderAdded() {
            return !ordersAdded.isEmpty() ? ordersAdded.get(0) : null;
        }

        @Override
        public boolean hasCustomerWithPhone(Phone phone) {
            requireNonNull(phone);
            return singleCustomer.getPhone().equals(phone);
        }

        @Override
        public Customer getCustomerWithPhone(Phone phone) {
            requireNonNull(phone);
            if (hasCustomerWithPhone(phone)) {
                return singleCustomer;
            } else {
                return null;
            }
        }

        @Override
        public void addOrder(Order order) {
            requireNonNull(order);
            ordersAdded.add(order);
        }

        @Override
        public GuiSettings getGuiSettings() {
            return userPrefs.getGuiSettings();
        }

        @Override
        public void setPanelToOrderList() {
            getGuiSettings().setPanelToOrderList();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
