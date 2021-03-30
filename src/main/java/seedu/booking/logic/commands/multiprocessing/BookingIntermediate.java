package seedu.booking.logic.commands.multiprocessing;

import java.util.Set;

import seedu.booking.logic.commands.AddBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

public class BookingIntermediate implements Intermediate<AddBookingCommand> {

    private Email bookerEmail;
    private VenueName venueName;
    private Description description;
    private StartTime bookingStart;
    private EndTime bookingEnd;
    private Set<Tag> tags;

    /**
     * Initialised a Booking Intermediate to store tempoary user input
     */
    public BookingIntermediate() {
        this.bookerEmail = null;
        this.venueName = null;
        this.description = null;
        this.bookingStart = null;
        this.bookingEnd = null;
        this.tags = null;
    }

    public void setEmail(Email bookerEmail) {
        this.bookerEmail = bookerEmail;
        System.out.println("Intermediate: " + this.bookerEmail.toString());
    }

    public void setVenueName(VenueName venueName) {
        this.venueName = venueName;
        System.out.println("Intermediate: " + this.venueName.toString());
    }

    public void setDescription(Description description) {
        this.description = description;
        System.out.println("Intermediate: " + this.description.toString());
    }

    public void setBookingStart(StartTime bookingStart) {
        this.bookingStart = bookingStart;
        System.out.println("Intermediate: " + this.bookingStart.toString());
    }

    public void setBookingEnd(EndTime bookingEnd) {
        this.bookingEnd = bookingEnd;
        System.out.println("Intermediate: " + this.bookingEnd.toString());
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;

        final StringBuilder builder = new StringBuilder();
        if (!this.tags.isEmpty()) {
            this.tags.forEach(builder::append);
        }
        System.out.println("Intermediate: " + builder.toString());
    }

    /**
     * Creates a Booking with the existing user input info
     */
    public Booking createBooking() {
        return new Booking(this.bookerEmail, this.venueName, this.description,
                this.bookingStart, this.bookingEnd, this.tags);
    }

    @Override
    public AddBookingCommand parse() throws ParseException {
        Booking booking = new Booking(bookerEmail, venueName, description,
                bookingStart, bookingEnd, tags);
        return new AddBookingCommand(booking);
    }

}
