package seedu.partyplanet.model.event;

import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Remark;

/**
 * Represents a Event in PartyPlanet.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private Name name;

    // Data fields
    private EventDate eventDate;
    private Remark details;
    private boolean isDone;

    /**
     * Default Event constructor
     */
    public Event(Name name, EventDate eventDate, Remark details) {
        this.name = name;
        this.eventDate = eventDate;
        this.details = details;
        this.isDone = false;
    }

    /**
     * Event constructor with a field for isDone
     */
    public Event(Name name, EventDate eventDate, Remark details, boolean isDone) {
        this.name = name;
        this.eventDate = eventDate;
        this.details = details;
        this.isDone = isDone;
    }


    /**
     * Returns name of event
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns date of event
     */
    public EventDate getEventDate() {
        return eventDate;
    }

    /**
     * Returns details of event
     */
    public Remark getDetails() {
        return details;
    }

    /**
     * Returns whether Event is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns event status.
     * Tick represents done, empty string represents not done.
     */
    public String getStatus() {
        return isDone ? "\u2713" : "";
    }

    /**
     * Returns an Event object that is done.
     */
    public Event setDone() {
        return new Event(getName(), getEventDate(), getDetails(), true);
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        // state check
        Event event = (Event) other;
        return getName().equals(event.getName())
                && getEventDate().equals(event.getEventDate())
                && getDetails().equals(event.getDetails())
                && isDone == event.isDone;

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        if (!EventDate.isEmptyDate(getEventDate())) {
            builder.append(("; Date: "))
                    .append(getEventDate());
        }
        if (!Remark.isEmptyRemark(getDetails())) {
            builder.append("; Details: ")
                    .append(getDetails());
        }
        return builder.toString();
    }
}
