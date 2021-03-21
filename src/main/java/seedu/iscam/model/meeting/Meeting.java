package seedu.iscam.model.meeting;

import static seedu.iscam.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.iscam.model.client.Location;
import seedu.iscam.model.client.Name;
import seedu.iscam.model.tag.Tag;

/**
 * Represents a Meeting in the address book.
 * Guarantees: identity fields are present and not null, data fields are validated, immutable.
 */
public class Meeting {
    private Name clientName;
    private LocalDateTime dateTime;

    // Data fields
    private Location location;
    private Description description;
    private Set<Tag> tags = new HashSet<>();
    private boolean isDone;

    /**
     * Every field must be present and not null.
     */
    public Meeting(Name clientName, LocalDateTime dateTime, Location location, Description description, Set<Tag> tags) {
        requireAllNonNull(clientName, dateTime, location, description, tags);
        this.clientName = clientName;
        this.dateTime = dateTime;

        this.location = location;
        this.description = description;
        this.tags = tags;
        this.isDone = false;
    }

    public Name getClientName() {
        return clientName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public Description getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Update the location where the meeting takes place.
     *
     * @param newLocation A validated new location.
     */
    public void relocate(Location newLocation) {
        this.location = newLocation;
    }

    /**
     * Update the date and time when the meeting takes place.
     *
     * @param newDateTime A validated date and time
     */
    public void reschedule(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    /**
     * Mark the meeting as done.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Returns true if both meetings have the same date and time.
     */
    public boolean isInConflict(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getDateTime().isEqual(this.dateTime);
    }

    /**
     * Returns true if both meetings have the same identity and date fields.
     * This defines a notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getClientName().equals(this.clientName)
                && otherMeeting.getDateTime().isEqual(this.dateTime)
                && otherMeeting.getLocation().equals(this.location)
                && otherMeeting.getDescription().equals(this.description)
                && otherMeeting.getTags().equals(this.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(clientName, dateTime, location, description, tags, isDone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(clientName.toString())
                .append("; Date & Time: ")
                .append(dateTime.toString())
                .append("; Location: ")
                .append(location.toString())
                .append("; Description: ")
                .append(description.toString());

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Completed: ")
                .append(isDone);

        return builder.toString();
    }
}
