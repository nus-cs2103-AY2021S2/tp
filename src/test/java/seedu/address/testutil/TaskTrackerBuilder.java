package seedu.address.testutil;

import seedu.address.model.TaskTracker;
import seedu.address.model.person.Task;

/**
 * A utility class to help with building TaskTracker objects.
 * Example usage: <br>
 *     {@code TaskTracker ab = new TaskTrackerBuilder().withTask("John", "Doe").build();}
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
     * Adds a new {@code Task} to the {@code TaskTracker} that we are building.
     */
    public TaskTrackerBuilder withTask(Task task) {
        taskTracker.addTask(task);
        return this;
    }

    public TaskTracker build() {
        return taskTracker;
    }
}
