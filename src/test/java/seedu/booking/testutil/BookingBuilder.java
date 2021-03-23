package seedu.booking.testutil;

import static seedu.booking.testutil.TypicalVenues.VENUE1;

import java.time.LocalDateTime;

import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {

    public static final Email DEFAULT_BOOKER = new Email("example@gmail.com");
    public static final VenueName DEFAULT_VENUE = VENUE1.getVenueName();
    private static final Description DEFAULT_DESCRIPTION = new Description("Good");
    private static final StartTime DEFAULT_BOOKING_START = new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00));
    private static final EndTime DEFAULT_BOOKING_END = new EndTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00));
    private static final Id DEFAULT_ID = new Id(1);

    private Email bookerEmail;
    private Description description;
    private VenueName venueName;
    private StartTime bookingStart;
    private EndTime bookingEnd;
    private Id id;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        bookerEmail = DEFAULT_BOOKER;
        venueName = DEFAULT_VENUE;
        description = DEFAULT_DESCRIPTION;
        bookingStart = DEFAULT_BOOKING_START;
        bookingEnd = DEFAULT_BOOKING_END;
        id = DEFAULT_ID;
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        bookerEmail = bookingToCopy.getBookerEmail();
        venueName = bookingToCopy.getVenueName();
        description = bookingToCopy.getDescription();
        bookingStart = bookingToCopy.getBookingStart();
        bookingEnd = bookingToCopy.getBookingEnd();
        id = bookingToCopy.getId();
    }

    /**
     * Sets the {@code booker} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBooker(Email booker) {
        this.bookerEmail = booker;
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code Booking} that we are building.
     */
    public BookingBuilder withVenue(VenueName venue) {
        this.venueName = venue;
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Booking} that we are building.
     */
    public BookingBuilder withDescription(Description description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code bookingStart} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBookingStart(StartTime bookingStart) {
        this.bookingStart = bookingStart;
        return this;
    }

    /**
     * Sets the {@code bookingEnd} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBookingEnd(EndTime bookingEnd) {
        this.bookingEnd = bookingEnd;
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Booking} that we are building.
     */
    public BookingBuilder withId(Id id) {
        this.id = id;
        return this;
    }


    public Booking build() {
        return new Booking(bookerEmail, venueName, description, bookingStart, bookingEnd, id);
    }

}
