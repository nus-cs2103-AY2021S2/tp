package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.pool.Pool;

public class TypicalAddressBook {
    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical pools and passengers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Pool pool : TypicalPools.getTypicalPools()) {
            ab.addPool(pool);
        }
        for (Passenger passenger : TypicalPassengers.getTypicalPassengers()) {
            ab.addPassenger(passenger);
        }
        return ab;
    }
}
