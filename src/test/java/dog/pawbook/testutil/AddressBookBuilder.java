package dog.pawbook.testutil;

import dog.pawbook.model.AddressBook;
import dog.pawbook.model.owner.Owner;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withOwner("John", "Doe").build();}
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
     * Adds a new {@code Owner} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withOwner(Owner owner) {
        addressBook.addOwner(owner);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
