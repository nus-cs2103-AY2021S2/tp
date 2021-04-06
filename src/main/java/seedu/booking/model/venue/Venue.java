package seedu.booking.model.venue;

import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.booking.commons.util.StringUtil;
import seedu.booking.model.Tag;


/**
 * Represents a venue in the booking list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Venue {

    // Identity field
    private final VenueName name;

    // Data fields
    private final Capacity capacity;
    private final String description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Venue(VenueName name, Capacity capacity, String description, Set<Tag> tags) {
        requireAllNonNull(name, capacity, description, tags);
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.tags.addAll(tags);
    }

    public VenueName getVenueName() {
        return name;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both venues have the same name (case-insensitive).
     * This defines a weaker notion of equality between two venues.
     */
    public boolean isSameVenue(Venue otherVenue) {
        if (otherVenue == this) {
            return true;
        }

        return otherVenue != null
                && StringUtil.containsWordIgnoreCase(this.getVenueName().removeSpacesWithinVenueName(),
                        otherVenue.getVenueName().removeSpacesWithinVenueName());
    }

    /**
     * Returns true if both bookings have the same data fields.
     * This notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Venue)) {
            return false;
        }

        Venue otherVenue = (Venue) other;
        return otherVenue.getVenueName().equals(getVenueName())
                && otherVenue.getCapacity().equals(getCapacity())
                && otherVenue.getDescription().equals(getDescription())
                && otherVenue.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, capacity, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getVenueName());

        Capacity capacity = getCapacity();
        if (capacity.venueCapacity > 0) {
            builder.append("; Capacity: ")
                    .append(getCapacity());
        }
        builder.append("; Description: ").append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
