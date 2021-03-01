package seedu.taskify.testutil;

import seedu.taskify.model.AddressBook;
import seedu.taskify.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withTask("John", "Doe").build();}
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
     * Adds a new {@code Task} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTask(Task task) {
        addressBook.addTask(task);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
