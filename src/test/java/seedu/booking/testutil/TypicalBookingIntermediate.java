package seedu.booking.testutil;

import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;

import java.time.LocalDateTime;
import java.util.HashSet;

import seedu.booking.logic.commands.multiprocessing.AddBookingIntermediate;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;

/**
 * A utility class containing a list of {@code AddBookingIntermediate} objects to be used in tests.
 */
public class TypicalBookingIntermediate {

    public static final AddBookingIntermediate BK_INTER1 = new AddBookingIntermediate(new Email("example1@gamil.com"),
            VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00)),
            new HashSet<>()
    );

    public static final AddBookingIntermediate BK_INTER2 = new AddBookingIntermediate(new Email("example2@gamil.com"),
            VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2022, 03, 01, 13, 00, 00)),
            new EndTime(LocalDateTime.of(2022, 03, 01, 14, 00, 00)),
            new HashSet<>()
    );

    public static final AddBookingIntermediate BK_INTER3 = new AddBookingIntermediate(new Email("example2@gamil.com"),
            VENUE2.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00)),
            null
    );


    public static final AddBookingIntermediate BK_INTER4 = new AddBookingIntermediate(new Email("example2@gamil.com"),
            VENUE2.getVenueName(),
            new Description("description"),
            null,
            null,
            null
    );
}
