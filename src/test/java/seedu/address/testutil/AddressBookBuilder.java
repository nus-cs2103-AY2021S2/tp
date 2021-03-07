package seedu.address.testutil;

import seedu.address.model.ModuleBook;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code ModuleBook ab = new ModuleBookBuilder().withTask("John", "Doe").build();}
 */
public class ModuleBookBuilder {

    private ModuleBook addressBook;

    public ModuleBookBuilder() {
        addressBook = new ModuleBook();
    }

    public ModuleBookBuilder(ModuleBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Task} to the {@code ModuleBook} that we are building.
     */
    public ModuleBookBuilder withTask(Task task) {
        addressBook.addTask(task);
        return this;
    }

    public ModuleBook build() {
        return addressBook;
    }
}
