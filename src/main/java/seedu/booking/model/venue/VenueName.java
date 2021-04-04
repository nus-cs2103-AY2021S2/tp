package seedu.booking.model.venue;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

import seedu.booking.commons.util.StringUtil;

/**
 * Represents a Venue's name in the booking system.
 */
public class VenueName {

    public static final String MESSAGE_CONSTRAINTS =
            "Venue names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the venue name must not be a whitespace,
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
        checkArgument(isValidVenueName(name), MESSAGE_CONSTRAINTS);
        venueName = name;
    }

    /**
     * Returns true if a given string is a valid venue name.
     */
    public static boolean isValidVenueName(String test) {
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

    /**
     * Returns true if both venues have the same venue name (case-insensitive).
     * This notion of equality between two venues.
     */
    public boolean isSameVenueName(Venue otherVenue) {
        return otherVenue.getVenueName() != null
                && StringUtil.containsWordIgnoreCase(this.removeSpacesWithinVenueName(),
                otherVenue.getVenueName().removeSpacesWithinVenueName());
    }

    public String removeSpacesWithinVenueName() {
        return this.venueName.replace(" ", "");
    }
}
