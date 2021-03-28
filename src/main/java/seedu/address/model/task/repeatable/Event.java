package seedu.address.model.task.repeatable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.Repeatable;

/**
 * Represents a Repeatable as an Event.
 */
public class Event extends Repeatable {

    /**
     * Constructor for Event.
     * @param description Description of the Event.
     * @param isWeekly isWeekly Status of the Event.
     * @param at Date of the Event.
     */
    public Event(String description, Boolean isWeekly, LocalDate at) {
        super(description, isWeekly, at);
    }

    /**
     * Constructor for Event.
     * @param description Description of the Event.
     * @param isWeekly isWeekly Status of the Event
     * @param at Date of the Event.
     * @param time Time of the Event.
     */
    public Event(String description, Boolean isWeekly, LocalDate at, LocalTime time) {
        super(description, isWeekly, at, time);
    }

    /**
     * Returns the isWeekly status of the Event.
     * @return A Boolean representing the Event's isWeekly status.
     */
    public Boolean getIsWeekly() {
        assert this.isWeekly != null;
        return this.isWeekly;
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
                && otherEvent.getIsWeekly().equals(getIsWeekly())
                && otherEvent.getTime().equals(getTime())
                && otherEvent.getAt().equals(getAt());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, isWeekly, at, time);
    }

    /**
     * Returns a String representation of the Event.
     * @return A String representation of the Event.
     */
    @Override
    public String toString() {
        if (time == null) {
            return this.description + " (at: " + DateUtil.decodeDate(at) + ")";
        }

        return this.description + " (at: " + DateUtil.decodeDate(at) + " " + TimeUtil.decodeTime(time) + ")";
    }
}
