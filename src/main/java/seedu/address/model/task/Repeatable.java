package seedu.address.model.task;

import java.time.LocalDate;

public abstract class Repeatable {

    protected String description;
    protected Boolean isDone;
    protected Interval interval;
    protected LocalDate at;

    /**
     * Constructor for Repeatable.
     * @param description Description of the Repeatable.
     */
    public Repeatable(String description, Interval interval,  LocalDate at) {
        this.description = description;
        this.interval = interval;
        this.at = at;
        this.isDone = false;
    }

    /**
     * Constructor for Repeatable.
     * @param description Description of the Repeatable.
     * @param isDone Marks whether the Repeatable is Done.
     */
    public Repeatable(String description, Interval interval, LocalDate at, Boolean isDone) {
        this.description = description;
        this.interval = interval;
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon dependent on the status of the Repeatable.
     * @return A string representing the Repeatable's status.
     */
    public String getStatusIcon() {
        assert isDone != null;
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the Repeatable description.
     * @return A String representing the Repeatable description.
     */
    public String getDescription() {
        assert this.description != null;
        return this.description;
    }

    /**
     * Returns the status of the Repeatable.
     * @return A Boolean representing the Repeatable's status.
     */
    public Boolean getIsDone() {
        assert this.isDone != null;
        return this.isDone;
    }

    /**
     * Returns the date of the Repeatable.
     * @return A LocalDate representing the Repeatable's date.
     */
    public LocalDate getAt() {
        assert this.at != null : "at should not be null!";
        return this.at;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the interval interval.
     * @return Interval interval.
     */
    public abstract Interval getRecurrence();

    /**
     * Sets the Interval interval to specified level.
     * @param interval Level of Interval.
     */
    public abstract void  setRecurrence(Interval interval);

    /**
     * Checks if an instance of a Repeatable is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    /**
     * Returns a String representation of the Repeatable.
     * @return A String representation of the Repeatable.
     */
    @Override
    public abstract String toString();
}
