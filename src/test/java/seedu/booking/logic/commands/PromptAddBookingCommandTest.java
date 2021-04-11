package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_BOOKING_VENUE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_VENUE;
import static seedu.booking.testutil.TypicalPersons.HOON;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

public class PromptAddBookingCommandTest {
    private ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @BeforeEach
    void setup() {
        try {
            new AddPersonCommand(HOON).execute(model);
        } catch (Exception ex) {
            throw new AssertionError("Execution of command should not fail.");
        }
    }

    @Test
    void execute() throws CommandException {
        CommandResult expectedResult = new CommandResult(PROMPT_BOOKING_VENUE_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT);
        CommandResult result = new PromptAddBookingCommand(HOON.getEmail()).execute(model);

        assertEquals(expectedResult, result);

        String state = StatefulLogicManager.getState();
        assertTrue(state.equals(STATE_VENUE));
        assertTrue(StatefulLogicManager.isStateActive());

        StatefulLogicManager.resetCommandState();
    }
}
