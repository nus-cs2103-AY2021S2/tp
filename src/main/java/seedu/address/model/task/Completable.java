package seedu.address.model.task;

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

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}