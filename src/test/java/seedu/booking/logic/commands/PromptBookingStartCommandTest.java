package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_END_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_EXIT_PROMPT;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_END;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_START;
import static seedu.booking.testutil.TypicalBookings.BOOKING1;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.StatefulLogicManager;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

public class PromptBookingStartCommandTest {
    private ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @BeforeEach
    void setup() {
        CommandState commandState = new AddBookingCommandState();
        StatefulLogicManager.setCommandState(commandState);
        StatefulLogicManager.setStateActive();
        StatefulLogicManager.setState(STATE_START);
    }

    @Test
    void execute() {
        PromptBookingStartCommand command = new PromptBookingStartCommand(BOOKING1.getBookingStart());
        CommandResult expectedResult = new CommandResult(PROMPT_END_MESSAGE + PROMPT_MESSAGE_EXIT_PROMPT,
                COMMAND_SHOW_BOOKINGS);
        CommandResult result;
        try {
            result = command.execute(model);
            assertEquals(expectedResult, result);
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.");
        }

        String state = StatefulLogicManager.getState();
        assertTrue(state.equals(STATE_END));
        assertTrue(StatefulLogicManager.isStateActive());

        StatefulLogicManager.resetCommandState();
    }
}
