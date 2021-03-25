package seedu.booking.model.venue;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

/**
 * Represents a Venue's name in the booking system.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class VenueName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String venueName;

    /**
     * Constructs a {@code VenueName}.
     *
     * @param name A valid venue name.
     */
    public VenueName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        venueName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return venueName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueName // instanceof handles nulls
                && venueName.equals(((VenueName) other).venueName)); // state check
    }

    @Override
    public int hashCode() {
        return venueName.hashCode();
    }

    public boolean isSameVenueName(Venue venue) {
        return this.venueName.equals(venue.getVenueName());
    }
}
