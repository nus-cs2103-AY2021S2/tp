package seedu.timeforwheels.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.timeforwheels.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.timeforwheels.testutil.Assert.assertThrows;
import static seedu.timeforwheels.testutil.TypicalCustomers.ALICE;
import static seedu.timeforwheels.testutil.TypicalCustomers.getTypicalDeliveryList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.exceptions.DuplicateCustomerException;
import seedu.timeforwheels.testutil.CustomerBuilder;

public class DeliveryListTest {

    private final DeliveryList deliveryList = new DeliveryList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), deliveryList.getCustomerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deliveryList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDeliveryList_replacesData() {
        DeliveryList newData = getTypicalDeliveryList();
        deliveryList.resetData(newData);
        assertEquals(newData, deliveryList);
    }

    @Test
    public void resetData_withDuplicateCustomers_throwsDuplicateCustomerException() {
        // Two customers with the same identity fields
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Customer> newCustomers = Arrays.asList(ALICE, editedAlice);
        DeliveryListStub newData = new DeliveryListStub(newCustomers);

        assertThrows(DuplicateCustomerException.class, () -> deliveryList.resetData(newData));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deliveryList.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInDeliveryList_returnsFalse() {
        assertFalse(deliveryList.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInDeliveryList_returnsTrue() {
        deliveryList.addCustomer(ALICE);
        assertTrue(deliveryList.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerWithSameIdentityFieldsInDeliveryList_returnsTrue() {
        deliveryList.addCustomer(ALICE);
        Customer editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(deliveryList.hasCustomer(editedAlice));
    }

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> deliveryList.getCustomerList().remove(0));
    }

    /**
     * A stub ReadOnlyDeliveryList whose customers list can violate interface constraints.
     */
    private static class DeliveryListStub implements ReadOnlyDeliveryList {
        private final ObservableList<Customer> customers = FXCollections.observableArrayList();

        DeliveryListStub(Collection<Customer> customers) {
            this.customers.setAll(customers);
        }

        @Override
        public ObservableList<Customer> getCustomerList() {
            return customers;
        }
    }

}
