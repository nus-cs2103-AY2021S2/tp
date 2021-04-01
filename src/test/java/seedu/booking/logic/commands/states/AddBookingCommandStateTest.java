package seedu.booking.logic.commands.states;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.TypicalAddBookingCommandState.BK_STATE1;
import static seedu.booking.testutil.TypicalAddBookingCommandState.BK_STATE2;
import static seedu.booking.testutil.TypicalAddBookingCommandState.BK_STATE3;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_END;
import static seedu.booking.commons.core.Messages.PROMPT_END_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;

public class AddBookingCommandStateTest {

    @Test
    void equals() {
        // same object -> returns true
        assertTrue(BK_STATE1.equals(BK_STATE1));

        // null -> returns false
        assertFalse(BK_STATE1.equals(BK_STATE2));

        // different type -> returns false
        assertFalse(BK_STATE1.equals(90909));

        // different AddBookingCommandState -> returns false
        assertFalse(BK_STATE1.equals(BK_STATE3));
    }

    @Test
    void setNextState() {
        BK_STATE1.setNextState();
        String state = BK_STATE1.getState();
        String nextPromptMsg = BK_STATE1.getNextPromptMessage();
        assertTrue(state.equals(STATE_END));
        assertTrue(nextPromptMsg.equals(PROMPT_END_MESSAGE));
    }


}
