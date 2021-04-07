package seedu.taskify.testutil;

import seedu.taskify.model.Taskify;
import seedu.taskify.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code TaskifyParser ab = new TaskifyBuilder().withTask("John", "Doe").build();}
 */
public class TaskifyBuilder {

    private Taskify taskify;

    public TaskifyBuilder() {
        taskify = new Taskify();
    }

    public TaskifyBuilder(Taskify taskify) {
        this.taskify = taskify;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskifyParser} that we are building.
     */
    public TaskifyBuilder withTask(Task task) {
        taskify.addTask(task);
        return this;
    }

    public Taskify build() {
        return taskify;
    }
}
