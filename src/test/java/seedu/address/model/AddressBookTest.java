package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPools.HOMEPOOL;
import static seedu.address.testutil.TypicalPools.HOMEPOOL_PASSENGERS;
import static seedu.address.testutil.TypicalPools.OFFICEPOOL_PASSENGERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.exceptions.DuplicatePassengerException;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.exceptions.DuplicatePoolException;
import seedu.address.testutil.PassengerBuilder;
import seedu.address.testutil.PoolBuilder;
import seedu.address.testutil.TypicalAddressBook;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPassengerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = TypicalAddressBook.getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePassengers_throwsDuplicatePassengerException() {
        // Two passengers with the same identity fields
        Passenger editedAlice = new PassengerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HR)
                .build();
        List<Passenger> newPassengers = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPassengers, new ArrayList<>());

        assertThrows(DuplicatePassengerException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPassenger_nullPassenger_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPassenger(null));
    }

    @Test
    public void hasPassenger_passengerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPassenger(ALICE));
    }

    @Test
    public void hasPassenger_passengerInAddressBook_returnsTrue() {
        addressBook.addPassenger(ALICE);
        assertTrue(addressBook.hasPassenger(ALICE));
    }

    @Test
    public void hasPassenger_passengerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPassenger(ALICE);
        Passenger editedAlice = new PassengerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HR)
                .build();
        assertTrue(addressBook.hasPassenger(editedAlice));
    }

    @Test
    public void hasPassenger_nullPassengerEqual_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEqualPassenger(null));
    }

    @Test
    public void hasPassenger_passengerEqualNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEqualPassenger(ALICE));
    }

    @Test
    public void hasPassenger_passengerEqualInAddressBook_returnsTrue() {
        addressBook.addPassenger(ALICE);
        assertTrue(addressBook.hasEqualPassenger(ALICE));
    }

    @Test
    public void hasPassenger_passengerEqualWithSameIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addPassenger(ALICE);
        Passenger editedAlice = new PassengerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HR)
                .build();
        assertFalse(addressBook.hasEqualPassenger(editedAlice));
    }

    @Test
    public void getPassengerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPassengerList().remove(0));
    }

    @Test
    public void resetData_withDuplicatePool_throwsDuplicatePoolException() {
        // Two pools with the same identity fields
        Pool editedHomePool = new PoolBuilder(HOMEPOOL)
                .withPassengers(OFFICEPOOL_PASSENGERS)
                .withTags(VALID_TAG_IT)
                .build();

        List<Pool> newPools = Arrays.asList(HOMEPOOL, editedHomePool);
        AddressBookStub newData = new AddressBookStub(new ArrayList<>(), newPools);

        assertThrows(DuplicatePoolException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPool_nullPool_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPool(null));
    }

    @Test
    public void hasPool_poolNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPool(HOMEPOOL));
    }

    @Test
    public void hasPool_poolInAddressBook_returnsTrue() {
        addressBook.addPool(HOMEPOOL);
        assertTrue(addressBook.hasPool(HOMEPOOL));
    }

    @Test
    public void hasPool_poolWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPool(HOMEPOOL);
        Pool editedHomePool = new PoolBuilder(HOMEPOOL)
                .withPassengers(OFFICEPOOL_PASSENGERS)
                .withTags(VALID_TAG_IT)
                .build();
        assertTrue(addressBook.hasPool(editedHomePool));
    }

    @Test
    public void getPoolList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPoolList().remove(0));
    }

    @Test
    public void hasPool_hasPoolWithPassenger_returnsTrue() {
        Pool editedHomePool = new PoolBuilder(HOMEPOOL)
                .withPassengers(HOMEPOOL_PASSENGERS)
                .build();
        addressBook.addPool(editedHomePool);

        for (Passenger passenger : HOMEPOOL_PASSENGERS) {
            assertTrue(addressBook.hasPoolWithPassenger(passenger));
        }
    }


    @Test
    public void hasPool_hasPoolWithoutPassenger_returnsFalse() {
        Pool editedHomePool = new PoolBuilder(HOMEPOOL)
                .withPassengers(HOMEPOOL_PASSENGERS)
                .build();
        addressBook.addPool(editedHomePool);

        for (Passenger passenger : OFFICEPOOL_PASSENGERS) {
            assertFalse(addressBook.hasPoolWithPassenger(passenger));
        }
    }


    @Test
    public void hasPool_hasPoolWithPassengerSameIdentity_returnsFalse() {
        Pool editedHomePool = new PoolBuilder(HOMEPOOL)
                .withPassengers(HOMEPOOL_PASSENGERS)
                .build();
        addressBook.addPool(editedHomePool);

        for (Passenger passenger : HOMEPOOL_PASSENGERS) {
            Passenger newPassenger = new PassengerBuilder(passenger)
                    .withAddress(VALID_ADDRESS_BOB)
                    .withTags(VALID_TAG_HR)
                    .build();
            assertFalse(addressBook.hasPoolWithPassenger(newPassenger));
        }
    }

    /**
     * A stub ReadOnlyAddressBook whose passengers list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        private final ObservableList<Pool> pools = FXCollections.observableArrayList();

        AddressBookStub(Collection<Passenger> passengers, Collection<Pool> pools) {
            this.passengers.setAll(passengers);
            this.pools.setAll(pools);
        }

        @Override
        public ObservableList<Passenger> getPassengerList() {
            return passengers;
        }

        @Override
        public ObservableList<Pool> getPoolList() {
            return pools;
        }
    }

}
