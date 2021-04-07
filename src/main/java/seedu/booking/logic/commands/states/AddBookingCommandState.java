package seedu.booking.logic.commands.states;

import static seedu.booking.commons.core.Messages.PROMPT_BOOKING_DESC_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_BOOKING_VENUE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_END_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_GENERAL_ERROR;
import static seedu.booking.commons.core.Messages.PROMPT_START_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;

import java.util.Set;

import seedu.booking.logic.commands.intermediatestate.AddBookingIntermediate;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

public class AddBookingCommandState extends CommandState {

    public static final String STATE_EMAIL = "BOOKING EMAIL";
    public static final String STATE_VENUE = "BOOKING VENUE";
    public static final String STATE_DESC = "BOOKING DESC";
    public static final String STATE_TAG = "BOOKING TAG";
    public static final String STATE_START = "BOOKING START";
    public static final String STATE_END = "BOOKING END";

    private AddBookingIntermediate addBookingIntermediate;

    /**
     * Initialises a BookingCommandState
     */
    public AddBookingCommandState() {
        super();
        this.addBookingIntermediate = new AddBookingIntermediate();
    }

    /**
     * Initialises a BookingCommandState
     */
    public AddBookingCommandState(boolean isActive, String state, String msg,
                                  AddBookingIntermediate addBookingIntermediate) {
        super(isActive, state, msg);
        this.addBookingIntermediate = addBookingIntermediate;
    }

    /**
     * Initialises a BookingCommandState with email provided
     */
    public AddBookingCommandState(Email email) {
        super();
        this.addBookingIntermediate = new AddBookingIntermediate(email);
    }

    public AddBookingIntermediate getAddBookingIntermediate() {
        return this.addBookingIntermediate;
    }

    @Override
    public void setNextState() {
        String state = this.getState();
        switch (state) {
        case STATE_EMAIL:
            this.setState(STATE_VENUE);
            this.setNextPromptMessage(PROMPT_BOOKING_VENUE_MESSAGE);
            break;
        case STATE_VENUE:
            this.setState(STATE_DESC);
            this.setNextPromptMessage(PROMPT_BOOKING_DESC_MESSAGE);
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
        default:
            this.setNextPromptMessage(PROMPT_GENERAL_ERROR);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void processInput(Object value) {
        String state = this.getState();
        switch (state) {
        case STATE_EMAIL:
            addBookingIntermediate.setEmail((Email) value);
            break;
        case STATE_VENUE:
            addBookingIntermediate.setVenueName((VenueName) value);
            break;
        case STATE_DESC:
            addBookingIntermediate.setDescription((Description) value);
            break;
        case STATE_TAG:
            addBookingIntermediate.setTags((Set<Tag>) value);
            break;
        case STATE_START:
            addBookingIntermediate.setBookingStart((StartTime) value);
            break;
        case STATE_END:
            addBookingIntermediate.setBookingEnd((EndTime) value);
            break;
        default:
            break;
        }
    }

    @Override
    public Booking create() {
        return addBookingIntermediate.createBooking();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddBookingCommandState)) {
            return false;
        }

        AddBookingCommandState otherState = (AddBookingCommandState) other;
        return otherState.getState().equals(super.getState())
                && otherState.getNextPromptMessage().equals(super.getNextPromptMessage())
                && otherState.isActive() == super.isActive()
                && otherState.getAddBookingIntermediate().equals(getAddBookingIntermediate());
    }
}
