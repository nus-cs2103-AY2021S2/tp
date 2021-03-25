package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCheese.CAMEMBERT;
import static seedu.address.testutil.TypicalCheese.FETA;
import static seedu.address.testutil.TypicalCheese.GOUDA;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalModels.getTypicalAddressBook;
import static seedu.address.testutil.TypicalOrder.ORDER_BRIE;
import static seedu.address.testutil.TypicalOrder.ORDER_CAMEMBERT;
import static seedu.address.testutil.TypicalOrder.ORDER_CAMEMBERT_2;
import static seedu.address.testutil.TypicalOrder.ORDER_FETA;
import static seedu.address.testutil.TypicalOrder.ORDER_GOUDA;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.exceptions.DuplicateCustomerException;
import seedu.address.model.order.Order;
import seedu.address.testutil.CheeseBuilder;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.OrderBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getCustomerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateCustomers_throwsDuplicateCustomerException() {
        // Two customers with the same identity fields
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Customer> newCustomers = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newCustomers);

        assertThrows(DuplicateCustomerException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);
        assertTrue(addressBook.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCustomer(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasCustomer(editedAlice));
    }

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getCustomerList().remove(0));
    }

    @Test
    public void checkAddressBook_addOrderWithoutCustomer_throwsIllegalArgumentException() {
        addressBook.addOrder(ORDER_BRIE);
        assertThrows(IllegalArgumentException.class,
            String.format(
                Messages.MESSAGE_INVALID_ORDER_CUSTOMER_ID,
                ORDER_BRIE.getOrderId().value
            ), () -> addressBook.checkAddressBook());
    }

    @Test
    public void checkAddressBook_addOrderWithoutCheese_throwsIllegalArgumentException() {
        addressBook.addCustomer(ALICE);
        addressBook.addOrder(ORDER_CAMEMBERT);
        assertThrows(IllegalArgumentException.class,
            String.format(
                Messages.MESSAGE_INVALID_ORDER_CHEESE_ID,
                ORDER_CAMEMBERT.getOrderId().value
            ), () -> addressBook.checkAddressBook());
    }

    @Test
    public void checkAddressBook_addSameCheeseToMultipleOrder_throwsIllegalArgumentException() {
        addressBook.addCustomer(ALICE);
        addressBook.addCustomer(BENSON);
        addressBook.addCheese(CAMEMBERT);
        addressBook.addOrder(new OrderBuilder(ORDER_CAMEMBERT).withCheeses(Set.of(CAMEMBERT.getCheeseId())).build());
        addressBook.addOrder(new OrderBuilder(ORDER_CAMEMBERT_2).withCheeses(Set.of(CAMEMBERT.getCheeseId())).build());
        assertThrows(IllegalArgumentException.class,
            String.format(
                Messages.MESSAGE_INVALID_CHEESE_MULTIPLE_ORDER,
                ORDER_CAMEMBERT_2.getOrderId().value,
                CAMEMBERT.getCheeseId().value
            ), () -> addressBook.checkAddressBook());
    }

    @Test
    public void checkAddressBook_cheeseNotAssigned_throwsIllegalArgumentException() {
        addressBook.addCustomer(BENSON);
        addressBook.addCheese(new CheeseBuilder(FETA).withAssignStatus(false).build());
        addressBook.addOrder(ORDER_FETA);
        assertThrows(IllegalArgumentException.class,
            String.format(
                Messages.MESSAGE_INVALID_CHEESE_NOT_ASSIGNED,
                ORDER_FETA.getOrderId().value,
                FETA.getCheeseId().value
            ), () -> addressBook.checkAddressBook());
    }

    @Test
    public void checkAddressBook_cheeseOrderTypeMismatch_throwsIllegalArgumentException() {
        addressBook.addCustomer(BENSON);
        addressBook.addCheese(new CheeseBuilder(GOUDA).withAssignStatus(true).build());
        addressBook.addOrder(new OrderBuilder(ORDER_CAMEMBERT_2).withCheeses(Set.of(GOUDA.getCheeseId())).build());
        assertThrows(IllegalArgumentException.class,
            String.format(
                Messages.MESSAGE_INVALID_ORDER_CHEESE_CHEESE_TYPE,
                ORDER_CAMEMBERT_2.getOrderId().value,
                GOUDA.getCheeseId().value
            ), () -> addressBook.checkAddressBook());
    }

    @Test
    public void checkAddressBook_invalidAssignedCheese_throwsIllegalArgumentException() {
        addressBook.addCustomer(BENSON);
        addressBook.addCheese(new CheeseBuilder(GOUDA).withAssignStatus(true).build());
        addressBook.addOrder(ORDER_GOUDA);
        assertThrows(IllegalArgumentException.class,
            String.format(
                Messages.MESSAGE_INVALID_ASSIGNED_CHEESE,
                GOUDA.getCheeseId().value
            ), () -> addressBook.checkAddressBook());
    }

    /**
     * A stub ReadOnlyAddressBook whose customers list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();
        private final ObservableList<Order> orders = FXCollections.observableArrayList();
        private final ObservableList<Cheese> cheeses = FXCollections.observableArrayList();

        AddressBookStub(Collection<Customer> customers) {
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Customer> getCustomerList() {
            return customers;
        }

        @Override
        public ObservableList<Order> getOrderList() {
            return orders;
        }

        @Override
        public ObservableList<Cheese> getCheeseList() {
            return cheeses;
        }
    }

}
