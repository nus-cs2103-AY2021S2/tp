package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.passenger.Passenger;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPassenger("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private final AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Passenger} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPassenger(Passenger passenger) {
        addressBook.addPassenger(passenger);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
