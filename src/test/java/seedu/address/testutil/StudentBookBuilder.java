package seedu.address.testutil;

import seedu.address.model.StudentBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Studentbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class StudentBookBuilder {

    private StudentBook studentBook;

    public StudentBookBuilder() {
        studentBook = new StudentBook();
    }

    public StudentBookBuilder(StudentBook studentBook) {
        this.studentBook = studentBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public StudentBookBuilder withPerson(Person person) {
        studentBook.addPerson(person);
        return this;
    }

    public StudentBook build() {
        return studentBook;
    }
}
