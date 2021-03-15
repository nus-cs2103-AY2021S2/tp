package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.venue.Venue;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Venue} objects to be used in tests.
 */
public class TypicalVenues {

    public static final Venue VENUE1 = new Venue("Venue1", 10);
    public static final Venue VENUE2 = new Venue("Venue1", 20);
    public static final Venue VENUE3 = new Venue("Venue3", 10);
    public static final Venue VENUE4 = new Venue("Venue4", 10);

    // Manually added - Venue's details found in {@code CommandTestUtil}
    public static final Venue HALL = new VenueBuilder()
            .withName(VALID_VENUE_NAME_HALL).withCapacity(VALID_VENUE_CAPACITY_HALL)
            .build();
    public static final Venue FIELD = new VenueBuilder()
            .withName(VALID_VENUE_NAME_FIELD).withCapacity(VALID_VENUE_CAPACITY_FIELD)
            .build();

    private TypicalVenues() {} // prevents instantiation
}
