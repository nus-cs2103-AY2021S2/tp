package seedu.booking.model.venue;

import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a venue in the booking list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Venue {

    // Data fields
    private final VenueName name;
    private final Capacity capacity;
    private final String description;

    /**
     * Every field must be present and not null.
     */
    public Venue(VenueName name, Capacity capacity, String description) {
        requireAllNonNull(name, capacity);
        if (capacity.venueCapacity <= -1) {
            throw new IllegalArgumentException("Capacity cannot be 0 or less.");
        }
        this.name = name;
        this.capacity = capacity;
        this.description = description;
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
     * Returns true if both venues have the same name
     * This defines a weaker notion of equality between two venues.
     */
    public boolean isSameVenue(Venue otherVenue) {
        if (otherVenue == this) {
            return true;
        }

        return otherVenue != null && this.name.equals(otherVenue.name);
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

        Venue otherBooking = (Venue) other;
        return otherBooking.getVenueName().equals(getVenueName())
                && otherBooking.getCapacity().equals(getCapacity())
                && otherBooking.getDescription().equals(getDescription());
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
        String description = getDescription();
        builder.append("; Description: ").append(getDescription());

        return builder.toString();
    }
}
