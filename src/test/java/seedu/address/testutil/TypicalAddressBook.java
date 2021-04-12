package seedu.address.testutil;

import static seedu.address.testutil.TypicalPools.HOMEPOOL;
import static seedu.address.testutil.TypicalPools.OFFICEPOOL;

import seedu.address.model.AddressBook;
import seedu.address.model.person.passenger.Passenger;

/**
 * A utility class containing an {@code AddressBook} object to be used in tests.
 */
public class TypicalAddressBook {
    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical pools and passengers.
     * @return An {@code AddressBook} with all the typical pools and passengers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addPool(HOMEPOOL);
        ab.addPool(OFFICEPOOL);
        for (Passenger passenger : TypicalPassengers.getTypicalPassengers()) {
            ab.addPassenger(passenger);
        }
        return ab;
    }
}
