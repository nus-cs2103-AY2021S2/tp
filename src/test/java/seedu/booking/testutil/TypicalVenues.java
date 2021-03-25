package seedu.booking.testutil;

import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_DESCRIPTION_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_DESCRIPTION_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;

import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * A utility class containing a list of {@code Venue} objects to be used in tests.
 */
public class TypicalVenues {

    public static final Venue VENUE1 = new Venue(new VenueName("Venue1"), new Capacity(10),
            "No description provided.");
    public static final Venue VENUE2 = new Venue(new VenueName("Venue2"), new Capacity(20),
            "No description provided.");
    public static final Venue VENUE3 = new Venue(new VenueName("Venue3"), new Capacity(10),
            "No description provided.");
    public static final Venue VENUE4 = new Venue(new VenueName("Venue4"), new Capacity(10),
            "No description provided.");
    public static final Venue VENUE5 = new Venue(new VenueName("Venue1"), new Capacity(10),
            "No description provided.");
    public static final Venue VENUE6 = new Venue(new VenueName("Venue1"), new Capacity(5),
            "No description provided.");

    // Manually added - Venue's details found in {@code CommandTestUtil}
    public static final Venue HALL = new VenueBuilder()
            .withName(VALID_VENUE_NAME_HALL).withCapacity(VALID_VENUE_CAPACITY_HALL)
            .withDescription(VALID_VENUE_DESCRIPTION_HALL)
            .build();
    public static final Venue FIELD = new VenueBuilder()
            .withName(VALID_VENUE_NAME_FIELD).withCapacity(VALID_VENUE_CAPACITY_FIELD)
            .withDescription(VALID_VENUE_DESCRIPTION_FIELD)
            .build();

    private TypicalVenues() {} // prevents instantiation
}
