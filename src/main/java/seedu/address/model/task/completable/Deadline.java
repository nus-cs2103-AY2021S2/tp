package seedu.address.model.task.completable;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.Completable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Completable with a Deadline.
 */
public class Deadline extends Completable {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     * @param description Description of the Completable.
     * @param by Deadline of the Completable.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        requireNonNull(by);
        this.by = by;
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the Completable.
     * @param isDone Marks whether the Completable is Done.
     * @param by Deadline of the Completable.
     */
    public Deadline(String description, Boolean isDone, LocalDate by) {
        super(description, isDone);
        requireNonNull(by);
        this.by = by;
    }

    /**
     * Returns the Deadline of the Completable.
     * @return A LocalDate representing the Completable Deadline.
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
     * Returns a String representation of the Completable.
     * @return A String representation of the Completable.
     */
    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description + " (by: " + DateUtil.decodeDate(by) + ")";
    }
}
