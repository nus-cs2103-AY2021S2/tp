package seedu.address.testutil;

import seedu.address.model.TaskTracker;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building TaskTracker objects.
 * Example usage: <br>
 *     {@code TaskTracker ab = new TaskTrackerBuilder().withPerson("John", "Doe").build();}
 */
public class TaskTrackerBuilder {

    private TaskTracker taskTracker;

    public TaskTrackerBuilder() {
        taskTracker = new TaskTracker();
    }

    public TaskTrackerBuilder(TaskTracker taskTracker) {
        this.taskTracker = taskTracker;
    }

    /**
     * Adds a new {@code Person} to the {@code TaskTracker} that we are building.
     */
    public TaskTrackerBuilder withPerson(Person person) {
        taskTracker.addPerson(person);
        return this;
    }

    public TaskTracker build() {
        return taskTracker;
    }
}
