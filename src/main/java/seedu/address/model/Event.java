package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.model.module.Description;

/**
 * Represent an event that occurs
 */
public abstract class Event implements Comparable<Event> {

    protected final Description description;
    protected final LocalDate date;

    protected Event(Description description, LocalDate date) {
        requireAllNonNull(description, date);
        this.description = description;
        this.date = date;
    }

    public Description getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    public abstract int compareTo(Event otherEvent);
}
