package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;

/**
 * Represents a Task with a Deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param by Deadline of the Task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "Deadline";
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param isDone Marks whether the Task is Done.
     * @param by Deadline of the Task.
     */
    public Deadline(String description, Boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
        this.taskType = "D";
    }

    /**
     * Returns the Deadline of the Task.
     * @return A LocalDate representing the Task Deadline.
     */
    public LocalDate getBy() {
        assert this.by != null : "by should not be null!";
        return this.by;
    }

    /**
     * Checks if an instance of a Deadline is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return otherDeadline.getDescription().equals(getDescription())
                && otherDeadline.getIsDone().equals(getIsDone())
                && otherDeadline.getBy().equals(getBy())
                && otherDeadline.getTaskType().equals(getTaskType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskType, description, isDone, by);
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description + " (by: " + DateUtil.decodeDate(by) + ")";
    }
}
