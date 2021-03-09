package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Date event for a Person in the FriendDex.
 */
public class Event {
    private final String date;
    private final String description;

    /**
     * Constructs a {@code Event}
     *
     * @param date A valid date.
     * @param description A description of the event.
     */
    public Event(String date, String description) {
        requireAllNonNull(date, description);
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s %s\n", getDate(), getDescription());
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
        return otherEvent.getDate().equals(getDate())
                && otherEvent.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description);
    }
}
