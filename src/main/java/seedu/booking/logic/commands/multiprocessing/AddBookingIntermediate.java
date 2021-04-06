package seedu.booking.logic.commands.multiprocessing;

import java.util.Set;
import java.util.logging.Logger;

import seedu.booking.MainApp;
import seedu.booking.commons.core.LogsCenter;
import seedu.booking.logic.commands.AddBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

public class AddBookingIntermediate implements Intermediate<AddBookingCommand> {
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private Email bookerEmail;
    private VenueName venueName;
    private Description description;
    private StartTime bookingStart;
    private EndTime bookingEnd;
    private Set<Tag> tags;

    /**
     * Initialises an AddBookingIntermediate object to store temporary user input.
     */
    public AddBookingIntermediate() {
        this.bookerEmail = null;
        this.venueName = null;
        this.description = null;
        this.bookingStart = null;
        this.bookingEnd = null;
        this.tags = null;
    }

    public void setEmail(Email bookerEmail) {
        this.bookerEmail = bookerEmail;
        logger.info("Intermediate: " + this.bookerEmail.toString());
    }

    public void setVenueName(VenueName venueName) {
        this.venueName = venueName;
        logger.info("Intermediate: " + this.venueName.toString());

    }

    public void setDescription(Description description) {
        this.description = description;
        logger.info("Intermediate: " + this.description.toString());
    }

    public void setBookingStart(StartTime bookingStart) {
        this.bookingStart = bookingStart;
        logger.info("Intermediate: " + this.bookingStart.toString());

    }

    public void setBookingEnd(EndTime bookingEnd) {
        this.bookingEnd = bookingEnd;
        logger.info("Intermediate: " + this.bookingEnd.toString());
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;

        final StringBuilder builder = new StringBuilder();
        if (!this.tags.isEmpty()) {
            this.tags.forEach(builder::append);
        }
        logger.info("Intermediate: " + builder.toString());
    }

    /**
     * Creates a Booking with the existing user input info.
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
