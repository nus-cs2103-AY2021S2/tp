package seedu.booking.testutil;

import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;

import java.time.LocalDateTime;

import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;


/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {

    public static final Booking BOOKING1 = new Booking(new Person(new Name("Booker1")), VENUE1, "description",
            LocalDateTime.of(2021, 03, 01, 12, 30, 00),
            LocalDateTime.of(2021, 03, 01, 13, 30, 00),
            0
    );

    public static final Booking BOOKING2 = new Booking(new Person(new Name("Booker2")), VENUE1, "description",
            LocalDateTime.of(2021, 03, 01, 13, 00, 00),
            LocalDateTime.of(2021, 03, 01, 14, 00, 00),
            1
    );

    public static final Booking BOOKING3 = new Booking(new Person(new Name("Booker2")), VENUE2, "description",
            LocalDateTime.of(2021, 03, 01, 12, 30, 00),
            LocalDateTime.of(2021, 03, 01, 13, 30, 00),
            2
    );


    public static final Booking BOOKING4 = new Booking(new Person(new Name("Booker2")), VENUE2, "description",
            LocalDateTime.of(2021, 03, 01, 14, 30, 00),
            LocalDateTime.of(2021, 03, 01, 15, 30, 00),
            3
    );

    private TypicalBookings() {} // prevents instantiation
}
