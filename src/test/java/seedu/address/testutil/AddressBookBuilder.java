package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder<T extends Person> {

    private AddressBook<T> addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook<>();
    }

    public AddressBookBuilder(AddressBook<T> addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder<T> withPerson(T person) {
        addressBook.addPerson(person);
        return this;
    }

    public AddressBook<T> build() {
        return addressBook;
    }
}
