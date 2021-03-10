package seedu.address.model.task;

public abstract class Completable {

    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     */
    public Completable(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Completable.
     * @param description Description of the Completable.
     * @param isDone Marks whether the Completable is Done.
     */
    public Completable(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon dependent on the status of the Completable.
     * @return A string representing the Completable's status.
     */
    public String getStatusIcon() {
        assert isDone != null;
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the Completable description.
     * @return A String representing the Completable description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the status of the Completable.
     * @return A Boolean representing the Completable's status.
     */
    public Boolean getIsDone() {
        assert this.isDone != null;
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if an instance of a Completable is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the Completable.
     * @return A String representation of the Completable.
     */
    @Override
    public abstract String toString();
}
