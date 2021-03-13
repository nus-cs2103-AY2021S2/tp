package seedu.address.model.task.deadline;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.CompletableTodo;

/**
 * Represents a CompletableTodo with a Deadline.
 */
public class Deadline extends CompletableTodo {
    /**
     * Constructor for Deadline.
     * @param description Description of the CompletableTodo.
     * @param by Deadline of the CompletableTodo.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
        requireNonNull(by);
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the CompletableTodo.
     * @param isDone Marks whether the CompletableTodo is Done.
     * @param by Deadline of the CompletableTodo.
     */
    public Deadline(String description, Boolean isDone, LocalDate by) {
        super(description, by, isDone);
        requireNonNull(by);
    }

    /**
     * Returns the Deadline of the CompletableTodo.
     * @return A LocalDate representing the CompletableTodo Deadline.
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
                && otherDeadline.getBy().equals(getBy());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, isDone, by);
    }

    /**
     * Returns a String representation of the CompletableTodo.
     * @return A String representation of the CompletableTodo.
     */
    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description + " (by: " + DateUtil.decodeDate(by) + ")";
    }
}
