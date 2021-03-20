package seedu.booking.model.venue;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

/**
 * Represents a rating of a review of a book in the application.
 * Guarantees: immutable; is valid as declared in {@link #isValidCapacity(Integer)}
 */
public class Capacity {

    public static final String MESSAGE_CONSTRAINTS = "Capacity cannot be 0 or less.";

    public final Integer venueCapacity;

    /**
     * Constructs an {@code Capacity}.
     *
     * @param venueCapacity A valid venue capacity.
     */
    public Capacity(Integer venueCapacity) {
        requireNonNull(venueCapacity);
        checkArgument(isValidCapacity(venueCapacity), MESSAGE_CONSTRAINTS);
        this.venueCapacity = venueCapacity;
    }


    /**
     * Returns true if a given Integer is a valid venue capacity.
     */
    public static boolean isValidCapacity(Integer test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return venueCapacity.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Capacity // instanceof handles nulls
                && venueCapacity.equals(((Capacity) other).venueCapacity)); // state check
    }

    @Override
    public int hashCode() {
        return venueCapacity.hashCode();
    }
}
