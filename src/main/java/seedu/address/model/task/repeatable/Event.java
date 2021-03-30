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
     *
     * @param description Description of the Event.
     * @param date Date of the Event.
     * @param time Time of the Event.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description, date, time);
    }

    /**
     * Constructor for Repeatable.
     *
     * @param description Description of the Repeatable.
     * @param date Date of the Repeatable.
     * @param time Time of the Repeatable.
     * @param isWeekly isWeekly Status of the Repeatable
     */
    public Event(String description, LocalDate date, LocalTime time, Boolean isWeekly) {
        super(description, date, time, isWeekly);
    }

    /**
     * Returns the isWeekly status of the Event.
     *
     * @return A Boolean representing the Event's isWeekly status.
     */
    public Boolean getIsWeekly() {
        assert this.isWeekly != null;
        return this.isWeekly;
    }

    /**
     * Checks if an instance of a Event is equal to another Object.
     *
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
                && otherEvent.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, isWeekly, date, time);
    }

    /**
     * Returns a String representation of the Event.
     *
     * @return A String representation of the Event.
     */
    @Override
    public String toString() {
        if (!getIsWeekly()) {
            return this.description + " (on: " + DateUtil.decodeDate(date) + " at:" + TimeUtil.decodeTime(time) + ")";
        }

        return this.description + " (every " + DateUtil.decodeDateIntoDay(date) + " at:" + TimeUtil.decodeTime(time)
                + ")";
    }
}
