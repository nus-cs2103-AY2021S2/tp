package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

/**
 * Represent an event that occurs
 */
public abstract class Event implements Comparable<Event> {

    protected final Description description;
    protected final LocalDateTime dateTime;
    protected final Tag tag;

    protected Event(Description description, LocalDateTime dateTime, Tag tag) {
        requireAllNonNull(description, dateTime);
        this.description = description;
        this.dateTime = dateTime;
        this.tag = tag;
    }

    public Description getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Tag getTag() {
        return tag;
    }

    @Override
    public int compareTo(Event otherEvent) {
        return dateTime.compareTo(otherEvent.dateTime);
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

}
