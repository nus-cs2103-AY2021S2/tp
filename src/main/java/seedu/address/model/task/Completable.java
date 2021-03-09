package seedu.address.model.task;

import java.util.Objects;

/**
 * Represents a Task as a Completable.
 */
public class Completable extends Task {

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     */
    public Completable(String description) {
        super(description);
        this.taskType = "Completable";
    }

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     * @param isDone Marks whether the Completable is Done.
     */
    public Completable(String description, Boolean isDone) {
        super(description, isDone);
        this.taskType = "Completable";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Completable)) {
            return false;
        }

        Completable otherCompletable = (Completable) other;
        return otherCompletable.getDescription().equals(getDescription())
                && otherCompletable.getIsDone().equals(getIsDone())
                && otherCompletable.getTaskType().equals(getTaskType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskType, description, isDone);
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
