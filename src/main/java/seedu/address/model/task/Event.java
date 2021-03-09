package seedu.address.model.task;

import seedu.address.commons.util.DateUtil;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Task as an Event.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param at Event date of the Task.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.taskType = "Event";
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
        this.taskType = "Event";
    }

    /**
     * Returns the date of the Event.
     * @return A LocalDate representing the Event date.
     */
    public LocalDate getAt() {
        assert this.at != null : "at should not be null!";
        return this.at;
    }

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
        return "[E]" + super.toString() + " (at: " + DateUtil.decodeDate(at) + ")";
    }
}