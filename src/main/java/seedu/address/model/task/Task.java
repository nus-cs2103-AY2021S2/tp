package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Task in HEY MATEz.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    // Identity fields
    private final Title title;

    // Data fields
    private final Description description;
    private final TaskStatus taskStatus;
    private final Deadline deadline;
    private final Priority priority;

    /**
     * Overloaded constructor which sets taskStatus to uncompleted and priority to unassigned by default
     */
    public Task(Title title, Description description, Deadline deadline) {
        requireAllNonNull(title, description, deadline);
        this.title = title;
        this.description = description;
        this.taskStatus = TaskStatus.UNCOMPLETED;
        this.deadline = deadline;
        this.priority = Priority.UNASSIGNED;
    }

    /**
     * Every field must be present and not null. Priority here is default to unassigned
     */
    public Task(Title title, Description description, Deadline deadline, TaskStatus taskStatus) {
        requireAllNonNull(title, description, deadline, taskStatus);
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.priority = Priority.UNASSIGNED;
    }


    /**
     * Overloaded constructor where every field must be present and not null.  Status set to default of UNCOMPLETED
     */
    public Task(Title title, Description description, Deadline deadline, Priority priority) {
        requireAllNonNull(title, description, deadline, priority);
        this.title = title;
        this.description = description;
        this.taskStatus = TaskStatus.UNCOMPLETED;
        this.deadline = deadline;
        this.priority = priority;
    }

    /**
     * Overloaded constructor where every field must be present and not null
     */
    public Task(Title title, Description description, Deadline deadline, TaskStatus taskStatus, Priority priority) {
        requireAllNonNull(title, description, deadline, taskStatus, priority);
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
        this.priority = priority;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns true if both tasks have the same title.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getDeadline().equals((getDeadline()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Task Status: ")
                .append(getTaskStatus())
                .append("; Priority: ")
                .append(getPriority());

        return builder.toString();
    }

}
