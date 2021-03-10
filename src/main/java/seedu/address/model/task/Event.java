package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;

/**
 * Represents a Task as an Event.
 */
public class Event extends Task implements Recurrable {
    protected LocalDate at;
    protected Recurrence recurrence;

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param at Event date of the Task.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.taskType = "E";
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param isDone Marks whether the Task is Done.
     * @param at Event date of the Task.
     */
    public Event(String description, Boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
        this.taskType = "E";
    }

    /**
     * Returns the date of the Event.
     * @return A LocalDate representing the Event date.
     */
    public LocalDate getAt() {
        assert this.at != null : "at should not be null!";
        return this.at;
    }

    /**
     * Returns the recurrence interval.
     * @return Recurrence interval.
     */
    @Override
    public Recurrence getRecurrence() {
        return this.recurrence;
    }

    /**
     * Sets the Recurrence interval to specified level.
     * @param recurrence Level of Recurrence.
     */
    @Override
    public void setRecurrence(Recurrence recurrence) {
        assert recurrence != null;
        this.recurrence = recurrence;
    }

    /**
     * Checks if an instance of a Event is equal to another Object.
     * @param other Object to be compared with.
     * @return True if both objects are equal. Else return false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getDescription().equals(getDescription())
                && otherEvent.getIsDone().equals(getIsDone())
                && otherEvent.getTaskType().equals(getTaskType());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskType, description, isDone, at);
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.description + " (at: " + DateUtil.decodeDate(at) + ")";
    }
}
