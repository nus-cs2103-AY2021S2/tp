package seedu.booking.testutil;

import seedu.booking.logic.commands.multiprocessing.AddBookingIntermediate;
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
 * A utility class containing a list of {@code AddBookingIntermediate} objects to be used in tests.
 */
public class TypicalBookingIntermediate {

    public static final AddBookingIntermediate BK_INTER1 = new AddBookingIntermediate(new Email("example1@gamil.com"), VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00)),
            new HashSet<>()
    );

    public static final AddBookingIntermediate BK_INTER2 = new AddBookingIntermediate(new Email("example2@gamil.com"), VENUE1.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2022, 03, 01, 13, 00, 00)),
            new EndTime(LocalDateTime.of(2022, 03, 01, 14, 00, 00)),
            new HashSet<>()
    );

    public static final AddBookingIntermediate BK_INTER3 = new AddBookingIntermediate(new Email("example2@gamil.com"), VENUE2.getVenueName(),
            new Description("description"),
            new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00)),
            new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00)),
            null
    );


    public static final AddBookingIntermediate BK_INTER4 = new AddBookingIntermediate(new Email("example2@gamil.com"), VENUE2.getVenueName(),
            new Description("description"),
            null,
            null,
            null
    );
}
