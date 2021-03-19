package seedu.partyplanet.model.event;

import seedu.partyplanet.model.person.Birthday;
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
    private Birthday date;
    private Remark details;

    /**
     * Default Event constructor
     */
    public Event(Name name, Birthday date, Remark details) {
        this.name = name;
        this.date = date;
        this.details = details;
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
    public Birthday getDate() {
        return date;
    }

    /**
     * Returns details of event
     */
    public Remark getDetails() {
        return details;
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
                && getDate().equals(event.getDate())
                && getDetails().equals(event.getDetails());

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        if (!Birthday.isEmptyBirthday(getDate())) {
            builder.append(("; Date: "))
                    .append(getDate());
        }
        if (!Remark.isEmptyRemark(getDetails())) {
            builder.append("; Details: ")
                    .append(getDetails());
        }
        return builder.toString();
    }



}
