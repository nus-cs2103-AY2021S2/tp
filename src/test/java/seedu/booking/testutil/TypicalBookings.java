package seedu.booking.testutil;

import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_BOOKER_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_BOOKER_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_DESCRIPTION_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_DESCRIPTION_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_END_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_END_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_ID_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_ID_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_START_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_START_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_TAGS_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_VENUE_NAME_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_VENUE_NAME_HALL;
import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.booking.model.BookingSystem;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {

    public static final Booking BOOKING1 = new Booking(new Email("example1@gamil.com"), VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30)),
            new HashSet<>(),
            new Id("0")
    );

    public static final Booking BOOKING2 = new Booking(new Email("example2@gamil.com"), VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2022, 03, 01, 13, 00)),
            new EndTime(LocalDateTime.of(2022, 03, 01, 14, 00)),
            new HashSet<>(),
            new Id("1")
    );

    public static final Booking BOOKING3 = new Booking(new Email("example2@gamil.com"), VENUE2.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30)),
            new HashSet<>(),
            new Id("2")
    );


    public static final Booking BOOKING4 = new Booking(new Email("example2@gamil.com"), VENUE2.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2022, 03, 01, 14, 30)),
            new EndTime(LocalDateTime.of(2022, 03, 01, 15, 30)),
            new HashSet<>(),
            new Id("3")
    );

    public static final Booking BOOKING_HALL = new BookingBuilder()
            .withVenue(VALID_BOOKING_VENUE_NAME_HALL).withBooker(VALID_BOOKING_BOOKER_EMAIL_AMY)
            .withDescription(VALID_BOOKING_DESCRIPTION_HALL)
            .withBookingStart(VALID_BOOKING_START_HALL)
            .withBookingEnd(VALID_BOOKING_END_HALL)
            .withTags(VALID_BOOKING_TAGS_HALL)
            .withId(VALID_BOOKING_ID_HALL)
            .build();

    public static final Booking BOOKING_FIELD = new BookingBuilder()
            .withVenue(VALID_BOOKING_VENUE_NAME_FIELD).withBooker(VALID_BOOKING_BOOKER_EMAIL_BOB)
            .withDescription(VALID_BOOKING_DESCRIPTION_FIELD)
            .withBookingStart(VALID_BOOKING_START_FIELD)
            .withBookingEnd(VALID_BOOKING_END_FIELD)
            .withTags(VALID_BOOKING_TAGS_HALL)
            .withId(VALID_BOOKING_ID_FIELD)
            .build();

    public static final Booking CURRENT_BOOKING = new BookingBuilder()
            .withVenue("HallNew").withBooker("johnd@example.com")
            .withDescription("edited")
            .withBookingStart("2012-01-31 22:59")
            .withBookingEnd("2012-01-31 23:59")
            .withTags("meeting")
            .withId("182273586")
            .build();


    private TypicalBookings() {} // prevents instantiation

    /**
     * Returns an {@code BookingSystem} with all the typical persons.
     */
    public static BookingSystem getTypicalBookingSystem() {
        BookingSystem ab = new BookingSystem();
        for (Booking booking : getTypicalBookings()) {
            ab.addBooking(booking);
        }
        return ab;
    }

    public static List<Booking> getTypicalBookings() {
        return new ArrayList<>(Arrays.asList(BOOKING1, BOOKING4));
    }
}
