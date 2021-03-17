package seedu.address.testutil;

import seedu.address.model.BookingSystem;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BookingSystem addressBook;

    public AddressBookBuilder() {
        addressBook = new BookingSystem();
    }

    public AddressBookBuilder(BookingSystem addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public BookingSystem build() {
        return addressBook;
    }
}
