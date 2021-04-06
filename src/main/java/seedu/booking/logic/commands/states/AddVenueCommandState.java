package seedu.booking.logic.commands.states;

import static seedu.booking.commons.core.Messages.PROMPT_GENERAL_ERROR;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_VENUE_DESC_MESSAGE;

import java.util.Set;

import seedu.booking.logic.commands.intermediatestate.AddVenueIntermediate;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

public class AddVenueCommandState extends CommandState {
    public static final String STATE_CAPACITY = "VENUE CAPACITY";
    public static final String STATE_DESC = "VENUE DESC";
    public static final String STATE_TAG = "VENUE TAG";

    private AddVenueIntermediate addVenueIntermediate;

    /**
     * Initialises a BookingCommandState
     */
    public AddVenueCommandState(VenueName venueName) {
        super();
        this.addVenueIntermediate = new AddVenueIntermediate(venueName);
    }

    @Override
    public void setNextState() {
        String state = this.getState();
        switch (state) {
        case STATE_CAPACITY:
            this.setState(STATE_DESC);
            this.setNextPromptMessage(PROMPT_VENUE_DESC_MESSAGE);
            break;
        case STATE_DESC:
            this.setState(STATE_TAG);
            this.setNextPromptMessage(PROMPT_TAG_MESSAGE);
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
        case STATE_CAPACITY:
            addVenueIntermediate.setCapacity((Capacity) value);
            break;
        case STATE_DESC:
            addVenueIntermediate.setDescription((String) value);
            break;
        case STATE_TAG:
            try {
                addVenueIntermediate.setTags((Set<Tag>) value);
            } catch (ClassCastException e) {
                throw new ClassCastException();
            }
            break;
        default:
        }
    }

    @Override
    public Venue create() {
        return addVenueIntermediate.createVenue();
    }
}
