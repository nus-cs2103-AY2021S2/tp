package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents an Event in Focuris.
 * Guarantees: details are present and not null, fields are validated, immutable.
 */
public class Event {

    // Identity Fields
    private final EventName eventName;
    private final EventTime timeStart;
    private final EventTime timeEnd;
    private final EventStatus status;

    // Data Fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Person> persons = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Event(EventName eventName, EventTime timeStart, EventTime timeEnd,
                 EventStatus status, Description description, Set<Tag> tags,
                 Set<Person> persons) {
        requireAllNonNull(eventName, timeStart, timeEnd, status, description, tags, persons);
        this.eventName = eventName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.status = status;
        this.description = description;
        this.tags.addAll(tags);
        this.persons.addAll(persons);
    }

    public EventName getName() {
        return this.eventName;
    }

    public EventTime getTimeStart() {
        return this.timeStart;
    }

    public EventTime getTimeEnd() {
        return this.timeEnd;
    }

    public EventStatus getStatus() {
        return this.status;
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    /**
     * Returns an immutable Person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Person> getPersons() {
        return Collections.unmodifiableSet(this.persons);
    }

    /**
     * Returns true if both events have the same name, start and end time.
     * This defines a weaker notion of equality between two Events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        if (otherEvent != null) {
            return otherEvent.getName().equals(getName())
                    && otherEvent.getTimeStart().equals(getTimeStart())
                    && otherEvent.getTimeEnd().equals(getTimeEnd());
        }

        return false;
    }

    /**
     * Returns true if both Events have the same identity and data fields.
     * This defines a stronger notion of equality between two Events.
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
        return otherEvent.getName().equals(getName())
                && otherEvent.getTimeStart().equals(getTimeStart())
                && otherEvent.getTimeEnd().equals(getTimeEnd())
                && otherEvent.getStatus().equals(getStatus())
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getTags().equals(getTags())
                && otherEvent.getPersons().equals(getPersons());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, timeStart, timeEnd, status, description,
                tags, persons);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Time Start: ")
                .append(getTimeStart())
                .append("; Time End: ")
                .append(getTimeEnd())
                .append("; Status: ")
                .append(getStatus())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Person> persons = getPersons();
        if (!persons.isEmpty()) {
            builder.append("; Persons: ");
            persons.forEach(builder::append);
        }

        return builder.toString();
    }

}