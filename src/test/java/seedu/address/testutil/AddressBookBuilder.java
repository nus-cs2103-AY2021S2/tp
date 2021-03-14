package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.person.PersonBook;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PersonBook personBook;

    public AddressBookBuilder() {
        personBook = new PersonBook();
    }

    public AddressBookBuilder(PersonBook personBook) {
        this.personBook = personBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        personBook.addPerson(person);
        return this;
    }

    public PersonBook build() {
        return personBook;
    }
}
