package seedu.booking.testutil;

import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;

import java.time.LocalDateTime;

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
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00)),
            new Id(0)
    );

    public static final Booking BOOKING2 = new Booking(new Email("example2@gamil.com"), VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 13, 00, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 14, 00, 00)),
            new Id(1)
    );

    public static final Booking BOOKING3 = new Booking(new Email("example2@gamil.com"), VENUE2.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00)),
            new Id(2)
    );


    public static final Booking BOOKING4 = new Booking(new Email("example2@gamil.com"), VENUE2.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 14, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 15, 30, 00)),
            new Id(3)
    );

    private TypicalBookings() {} // prevents instantiation
}
