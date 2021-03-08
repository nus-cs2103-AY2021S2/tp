package seedu.module.testutil;

import seedu.module.model.ModuleBook;
import seedu.module.model.task.Task;

/**
 * A utility class to help with building Modulebook objects.
 * Example usage: <br>
 *     {@code ModuleBook ab = new ModuleBookBuilder().withTask("John", "Doe").build();}
 */
public class ModuleBookBuilder {

    private ModuleBook moduleBook;

    public ModuleBookBuilder() {
        moduleBook = new ModuleBook();
    }

    public ModuleBookBuilder(ModuleBook moduleBook) {
        this.moduleBook = moduleBook;
    }

    /**
     * Adds a new {@code Task} to the {@code ModuleBook} that we are building.
     */
    public ModuleBookBuilder withTask(Task task) {
        moduleBook.addTask(task);
        return this;
    }

    public ModuleBook build() {
        return moduleBook;
    }
}
