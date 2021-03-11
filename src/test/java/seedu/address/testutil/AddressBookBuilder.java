package seedu.address.testutil;

import seedu.address.model.ResidenceTracker;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ResidenceTracker addressBook;

    public AddressBookBuilder() {
        addressBook = new ResidenceTracker();
    }

    public AddressBookBuilder(ResidenceTracker addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public ResidenceTracker build() {
        return addressBook;
    }
}
