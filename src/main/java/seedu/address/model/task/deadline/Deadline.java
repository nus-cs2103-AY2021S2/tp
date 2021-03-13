package seedu.address.model.task.deadline;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.CompletableDeadline;

/**
 * Represents a CompletableDeadline as a Deadline.
 */
public class Deadline extends CompletableDeadline {

    /**
     * Constructor for Deadline.
     * @param description Description of the Deadline.
     * @param by Deadline of the Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the Deadline.
     * @param isDone Marks whether the Deadline is Done.
     * @param by Deadline of the Deadline.
     */
    public Deadline(String description, LocalDate by, Boolean isDone) {
        super(description, by, isDone);
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
     * Returns a String representation of the Deadline.
     * @return A String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description + " (by: " + DateUtil.decodeDate(by) + ")";
    }
}
