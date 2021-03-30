package seedu.booking.logic.commands.states;


import static seedu.booking.commons.core.Messages.PROMPT_DESC_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_END_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_START_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_VENUE_MESSAGE;

import seedu.booking.logic.commands.multiprocessing.BookingIntermediate;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

public class BookingCommandState extends CommandState {

    public static final String STATE_EMAIL = "EMAIL";
    public static final String STATE_VENUE = "VENUE";
    public static final String STATE_DESC = "DESC";
    public static final String STATE_START = "START";
    public static final String STATE_END = "END";

    private BookingIntermediate bookingIntermediate;

    /**
     * Initalises a BookingCommandState
     */
    public BookingCommandState() {
        super();
        this.bookingIntermediate = new BookingIntermediate();
    }

    @Override
    public void setNextState() {
        String state = this.getState();
        if (state.equals(STATE_EMAIL)) {
            this.setState(STATE_VENUE);
            this.setNextPromptMessage(PROMPT_VENUE_MESSAGE);
        } else if (state.equals(STATE_VENUE)) {
            this.setState(STATE_DESC);
            this.setNextPromptMessage(PROMPT_DESC_MESSAGE);
        } else if (state.equals(STATE_DESC)) {
            this.setState(STATE_START);
            this.setNextPromptMessage(PROMPT_START_MESSAGE);
        } else if (state.equals(STATE_START)) {
            this.setState(STATE_END);
            this.setNextPromptMessage(PROMPT_END_MESSAGE);
        }
    }

    @Override
    public void processInput(Object value) {
        String state = this.getState();
        if (state.equals(STATE_EMAIL)) {
            bookingIntermediate.setEmail((Email) value);
        } else if (state.equals(STATE_VENUE)) {
            bookingIntermediate.setVenueName((VenueName) value);
        } else if (state.equals(STATE_DESC)) {
            bookingIntermediate.setDescription((Description) value);
        } else if (state.equals(STATE_START)) {
            bookingIntermediate.setBookingStart((StartTime) value);
        } else if (state.equals(STATE_END)) {
            bookingIntermediate.setBookingEnd((EndTime) value);
        }
    }

    @Override
    public Booking create() {
        return bookingIntermediate.createBooking();
    }
}
