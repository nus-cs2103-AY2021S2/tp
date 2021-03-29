package seedu.booking.logic.commands.multiprocessing;

import seedu.booking.logic.commands.AddBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
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

    /**
     * Initialised a Booking Intermediate to store tempoary user input
     */
    public BookingIntermediate() {
        this.bookerEmail = null;
        this.venueName = null;
        this.description = null;
        this.bookingStart = null;
        this.bookingEnd = null;
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

    /**
     * Creates a Booking with the existing user input info
     */
    public Booking createBooking() {
        System.out.println("Intermediate: " + this.bookingEnd.toString());
        System.out.println("Intermediate: " + this.bookingStart.toString());
        System.out.println("Intermediate: " + this.bookerEmail.toString());
        System.out.println("Intermediate: " + this.venueName.toString());
        System.out.println("Intermediate: " + this.description.toString());
        return new Booking(this.bookerEmail, this.venueName, this.description, this.bookingStart, this.bookingEnd);
    }

    @Override
    public AddBookingCommand parse() throws ParseException {
        Booking booking = new Booking(bookerEmail, venueName, description,
                bookingStart, bookingEnd);
        return new AddBookingCommand(booking);
    }

}
