package seedu.booking.testutil;

import static seedu.booking.testutil.TypicalVenues.VENUE1;

import java.time.LocalDateTime;

import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {

    public static final Person DEFAULT_BOOKER = new Person(new Name("John"));
    public static final Venue DEFAULT_VENUE = VENUE1;
    private static final String DEFAULT_DESCRIPTION = "Good";
    private static final LocalDateTime DEFAULT_BOOKING_START = LocalDateTime.of(2021, 03, 01, 12, 30, 00);
    private static final LocalDateTime DEFAULT_BOOKING_END = LocalDateTime.of(2021, 03, 01, 12, 30, 00);
    private static final int DEFAULT_ID = 1;

    private Person booker;
    private String description;
    private Venue venue;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
    private int id;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        booker = DEFAULT_BOOKER;
        venue = DEFAULT_VENUE;
        description = DEFAULT_DESCRIPTION;
        bookingStart = DEFAULT_BOOKING_START;
        bookingEnd = DEFAULT_BOOKING_END;
        id = DEFAULT_ID;
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        booker = bookingToCopy.getBooker();
        venue = bookingToCopy.getVenue();
        description = bookingToCopy.getDescription();
        bookingStart = bookingToCopy.getBookingStart();
        bookingEnd = bookingToCopy.getBookingEnd();
        id = bookingToCopy.getId();
    }

    /**
     * Sets the {@code booker} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBooker(Person booker) {
        this.booker = booker;
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code Booking} that we are building.
     */
    public BookingBuilder withVenue(Venue venue) {
        this.venue = venue;
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Booking} that we are building.
     */
    public BookingBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code bookingStart} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBookingStart(LocalDateTime bookingStart) {
        this.bookingStart = bookingStart;
        return this;
    }

    /**
     * Sets the {@code bookingEnd} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBookingEnd(LocalDateTime bookingEnd) {
        this.bookingEnd = bookingEnd;
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Booking} that we are building.
     */
    public BookingBuilder withId(int id) {
        this.id = id;
        return this;
    }


    public Booking build() {
        return new Booking(booker, venue, description, bookingStart, bookingEnd, id);
    }

}
