package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Customer;
import seedu.address.model.person.exceptions.DuplicateCustomerException;
import seedu.address.testutil.PersonBuilder;

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
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        DeliveryList newData = getTypicalAddressBook();
        deliveryList.resetData(newData);
        assertEquals(newData, deliveryList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two customers with the same identity fields
        Customer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Customer> newCustomers = Arrays.asList(ALICE, editedAlice);
        DeliveryListStub newData = new DeliveryListStub(newCustomers);

        assertThrows(DuplicateCustomerException.class, () -> deliveryList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deliveryList.hasCustomer(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(deliveryList.hasCustomer(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        deliveryList.addCustomer(ALICE);
        assertTrue(deliveryList.hasCustomer(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        deliveryList.addCustomer(ALICE);
        Customer editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(deliveryList.hasCustomer(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
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
