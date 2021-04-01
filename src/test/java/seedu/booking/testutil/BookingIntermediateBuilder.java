package seedu.booking.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.booking.logic.commands.multiprocessing.AddBookingIntermediate;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.VenueName;

public class BookingIntermediateBuilder {
    public static final String DEFAULT_EMAIL = "example1@gamil.com";
    public static final String DEFAULT_VENUENAME = "Victoria Hall";
    public static final String DEFAULT_DESCRIPTION = "No description provided.";

    private Email bookerEmail;
    private VenueName venueName;
    private Description description;
    private StartTime bookingStart;
    private EndTime bookingEnd;
    private Set<Tag> tags;

    /**
     * Creates a {@code BookingIntermediateBuilder} with the default details.
     */
    public BookingIntermediateBuilder() {
        bookerEmail = new Email(DEFAULT_EMAIL);
        venueName = new VenueName(DEFAULT_VENUENAME);
        description = new Description(DEFAULT_DESCRIPTION);
        bookingStart = new StartTime(LocalDateTime.of(2021, 03, 01, 12, 30, 00));
        bookingEnd = new EndTime(LocalDateTime.of(2021, 03, 01, 13, 30, 00));
        tags = new HashSet<>();
    }


    public BookingIntermediateBuilder withEmail(Email email) {
        this.bookerEmail = email;
        return this;
    }

    public BookingIntermediateBuilder withVenueName(VenueName venueName) {
        this.venueName = venueName;
        return this;
    }

    public BookingIntermediateBuilder withDescription(Description description) {
        this.description = description;
        return this;
    }

    public BookingIntermediateBuilder withStartTime(StartTime startTime) {
        this.bookingStart = startTime;
        return this;
    }

    public BookingIntermediateBuilder withEndTime(EndTime endTime) {
        this.bookingEnd = endTime;
        return this;
    }

    public BookingIntermediateBuilder withTags(Set<Tag> tagset) {
        this.tags = tagset;
        return this;
    }

    public AddBookingIntermediate build() {
        return new AddBookingIntermediate(bookerEmail, venueName, description, bookingStart, bookingEnd,
                tags);
    }
}
