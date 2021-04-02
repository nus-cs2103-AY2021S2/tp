package seedu.booking.testutil;

import static seedu.booking.commons.core.Messages.PROMPT_END_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_START;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_TAG;
import static seedu.booking.testutil.TypicalBookingIntermediate.BK_INTER3;
import static seedu.booking.testutil.TypicalBookingIntermediate.BK_INTER4;

import seedu.booking.logic.commands.multiprocessing.AddBookingIntermediate;
import seedu.booking.logic.commands.states.AddBookingCommandState;

public class TypicalAddBookingCommandState {

    public static final AddBookingCommandState BK_STATE1 = new AddBookingCommandState(true, STATE_START,
            PROMPT_END_MESSAGE, BK_INTER4);

    public static final AddBookingCommandState BK_STATE2 = new AddBookingCommandState(true, STATE_TAG,
            PROMPT_TAG_MESSAGE,
            BK_INTER3);

    public static final AddBookingCommandState BK_STATE3 = new AddBookingCommandState(false, "DEFAULT",
            "DEFAULT",
            new AddBookingIntermediate());
}
