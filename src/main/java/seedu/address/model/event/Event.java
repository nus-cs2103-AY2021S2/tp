package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents an Event in Focuris.
 * Guarantees: details are present and not null, fields are validated, immutable.
 */
public class Event {
    /** Used to ensure events have a counter, for deletion and editing */
    private static int counter = 1;

    // Identity Fields
    private final int identifier;
    private final EventName eventName;
    // private final EventTime timeStart; // commented out for v1.2
    // private final EventTime timeEnd; // commented out for v1.2
    private final EventStatus status;

    // Data Fields
    private final Description description;
    private final EventPriority priority;

    /**
     * Every field must be present and not null.
     */
    public Event(EventName eventName, EventStatus status, EventPriority priority, Description description) {
        this.priority = priority;
        requireAllNonNull(eventName, status, description);
        this.identifier = counter;
        this.eventName = eventName;
        // this.timeStart = timeStart; // commented out for v1.2
        // this.timeEnd = timeEnd; // commented out for v1.2
        this.status = status;
        this.description = description;
        // this.tags.addAll(tags); // commented out for v1.2
        // this.persons.addAll(persons); // commented out for v1.2
        counter += 1;
    }

    /* for editing events, counter should not increase */

    /**
     * Every field must be filled. Used for edit events where the identifier should not increase.
     */
    public Event(EventName name, EventStatus status, EventPriority priority, Description description, int identifier) {
        requireAllNonNull(name, status, priority, description, identifier);
        this.eventName = name;
        this.status = status;
        this.priority = priority;
        this.description = description;
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public EventName getName() {
        return this.eventName;
    }

    /* Commented out for v1.2
    public EventTime getTimeStart() {
        return this.timeStart;
    }
     */

    /* Commented out for v1.2
    public EventTime getTimeEnd() {
        return this.timeEnd;
    }
     */

    public EventStatus getStatus() {
        return this.status;
    }

    public Description getDescription() {
        return this.description;
    }

    public EventPriority getPriority() {
        return this.priority;
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two Events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        if (otherEvent != null) {
            return otherEvent.getName().equals(getName());
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
                && otherEvent.getStatus().equals(getStatus())
                && otherEvent.getPriority().equals(getPriority())
                && otherEvent.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, priority, description, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Description: ")
                .append(getDescription())
                .append("; Status: ")
                .append(getStatus());

        return builder.toString();
    }

}
