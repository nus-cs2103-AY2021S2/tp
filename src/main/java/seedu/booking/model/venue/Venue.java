package seedu.booking.model.venue;

import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a venue in the booking list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Venue {

    // Data fields
    private final String name;
    private final int capacity;

    /**
     * Every field must be present and not null.
     */
    public Venue(String name, int capacity) {
        requireAllNonNull(name, capacity);
        if (capacity <= -1) {
            throw new IllegalArgumentException("Capacity cannot be 0 or less.");
        }
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
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
        return otherBooking.getName().equals(getName())
                && otherBooking.getCapacity() == getCapacity();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, capacity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName());

        int capacity = getCapacity();
        if (capacity != 0) {
            builder.append("; Capacity: ")
                    .append(getCapacity());
        }

        return builder.toString();
    }
}
