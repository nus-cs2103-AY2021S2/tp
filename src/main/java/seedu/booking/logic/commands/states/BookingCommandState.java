package seedu.booking.logic.commands.states;


import static seedu.booking.commons.core.Messages.PROMPT_DESC_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_END_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_START_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_VENUE_MESSAGE;

import java.util.Set;

import seedu.booking.logic.commands.multiprocessing.BookingIntermediate;
import seedu.booking.model.Tag;
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
    public static final String STATE_TAG = "TAG";
    public static final String STATE_START = "START";
    public static final String STATE_END = "END";

    private BookingIntermediate bookingIntermediate;

    /**
     * Initialises a BookingCommandState
     */
    public BookingCommandState() {
        super();
        this.bookingIntermediate = new BookingIntermediate();
    }

    @Override
    public void setNextState() {
        String state = this.getState();
        switch (state) {
        case STATE_EMAIL:
            this.setState(STATE_VENUE);
            this.setNextPromptMessage(PROMPT_VENUE_MESSAGE);
            break;
        case STATE_VENUE:
            this.setState(STATE_DESC);
            this.setNextPromptMessage(PROMPT_DESC_MESSAGE);
            break;
        case STATE_DESC:
            this.setState(STATE_TAG);
            this.setNextPromptMessage(PROMPT_TAG_MESSAGE);
            break;
        case STATE_TAG:
            this.setState(STATE_START);
            this.setNextPromptMessage(PROMPT_START_MESSAGE);
            break;
        case STATE_START:
            this.setState(STATE_END);
            this.setNextPromptMessage(PROMPT_END_MESSAGE);
            break;
        }
    }

    @Override
    public void processInput(Object value) {
        String state = this.getState();
        switch (state) {
        case STATE_EMAIL:
            bookingIntermediate.setEmail((Email) value);
            break;
        case STATE_VENUE:
            bookingIntermediate.setVenueName((VenueName) value);
            break;
        case STATE_DESC:
            bookingIntermediate.setDescription((Description) value);
            break;
        case STATE_TAG:
            bookingIntermediate.setTags((Set<Tag>) value);
            break;
        case STATE_START:
            bookingIntermediate.setBookingStart((StartTime) value);
            break;
        case STATE_END:
            bookingIntermediate.setBookingEnd((EndTime) value);
            break;
        }
    }

    @Override
    public Booking create() {
        return bookingIntermediate.createBooking();
    }
}
