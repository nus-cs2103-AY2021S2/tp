package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.module.Description;

/**
 * Represent an event that occurs
 */
public abstract class Event implements Comparable<Event> {

    protected final Description description;
    protected final LocalDateTime dateTime;

    protected Event(Description description, LocalDateTime dateTime) {
        requireAllNonNull(description, dateTime);
        this.description = description;
        this.dateTime = dateTime;
    }

    public Description getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    public int compareTo(Event otherEvent) {
        return dateTime.compareTo(otherEvent.dateTime);
    }
}
