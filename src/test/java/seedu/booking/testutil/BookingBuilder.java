package seedu.booking.testutil;

import static seedu.booking.testutil.TypicalVenues.VENUE1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.util.SampleDataUtil;
import seedu.booking.model.venue.VenueName;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {

    public static final String DEFAULT_BOOKER = "example@gmail.com";
    public static final String DEFAULT_VENUE = VENUE1.getVenueName().venueName;
    private static final String DEFAULT_DESCRIPTION = "Good";
    private static final String DEFAULT_BOOKING_START = "2021-03-01 12:30";
    private static final String DEFAULT_BOOKING_END = "2021-03-01 13:30";
    private static final String DEFAULT_ID = String.valueOf(1);

    private Email bookerEmail;
    private Description description;
    private VenueName venueName;
    private StartTime bookingStart;
    private EndTime bookingEnd;
    private Set<Tag> tags;
    private Id id;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        bookerEmail = new Email(DEFAULT_BOOKER);
        venueName = new VenueName(DEFAULT_VENUE);
        description = new Description(DEFAULT_DESCRIPTION);
        bookingStart = new StartTime(LocalDateTime.parse(DEFAULT_BOOKING_START, formatter));
        bookingEnd = new EndTime(LocalDateTime.parse(DEFAULT_BOOKING_END, formatter));
        tags = new HashSet<>();
        id = new Id(DEFAULT_ID);
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
        tags = new HashSet<>(bookingToCopy.getTags());
        id = bookingToCopy.getId();
    }

    /**
     * Sets the {@code booker} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBooker(String booker) {
        this.bookerEmail = new Email(booker);
        return this;
    }

    /**
     * Sets the {@code venue} of the {@code Booking} that we are building.
     */
    public BookingBuilder withVenue(String venue) {
        this.venueName = new VenueName(venue);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Booking} that we are building.
     */
    public BookingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code bookingStart} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBookingStart(String bookingStart) {
        this.bookingStart = new StartTime(LocalDateTime.parse(bookingStart, formatter));
        return this;
    }

    /**
     * Sets the {@code bookingEnd} of the {@code Booking} that we are building.
     */
    public BookingBuilder withBookingEnd(String bookingEnd) {
        this.bookingEnd = new EndTime(LocalDateTime.parse(bookingEnd, formatter));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Venue} that we are building.
     */
    public BookingBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code id} of the {@code Booking} that we are building.
     */
    public BookingBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code email} of the {@code Booking} that we are building.
     */
    public BookingBuilder withEmail(String bookerEmail) {
        this.bookerEmail = new Email(bookerEmail);
        return this;
    }

    public Booking build() {
        return new Booking(bookerEmail, venueName, description, bookingStart, bookingEnd, tags, id);
    }

}
