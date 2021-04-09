package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.resident.Resident;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withResident("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Resident} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withResident(Resident resident) {
        addressBook.addResident(resident);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
