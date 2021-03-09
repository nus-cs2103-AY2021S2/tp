package seedu.address.testutil;

import seedu.address.model.booking.Venue;

/**
 * A utility class containing a list of {@code Venue} objects to be used in tests.
 */
public class TypicalVenues {

    public static final Venue VENUE1 = new Venue("Venue1", 10);
    public static final Venue VENUE2 = new Venue("Venue1", 20);
    public static final Venue VENUE3 = new Venue("Venue3", 10);
    public static final Venue VENUE4 = new Venue("Venue4", 10);

    private TypicalVenues() {} // prevents instantiation
}
