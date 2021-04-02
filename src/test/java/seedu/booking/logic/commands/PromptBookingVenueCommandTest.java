package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_VENUE_NAME;
import static seedu.booking.commons.core.Messages.PROMPT_BOOKING_DESC_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_DESC;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_VENUE;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalVenues.VENUE1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.venue.VenueName;

public class PromptBookingVenueCommandTest {
    private ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @BeforeEach
    void setup() {
        CommandState commandState = new AddBookingCommandState();
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
        ModelManager.setState(STATE_VENUE);
        model.addVenue(VENUE1);
    }

    @Test
    void execute_validVenueName_success() {
        PromptBookingVenueCommand command = new PromptBookingVenueCommand(VENUE1.getVenueName());
        CommandResult expectedResult = new CommandResult(PROMPT_BOOKING_DESC_MESSAGE);
        CommandResult result;
        try {
            result = command.execute(model);
            assertEquals(expectedResult, result);
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.");
        }

        String state = ModelManager.getState();
        assertTrue(state.equals(STATE_DESC));
        assertTrue(ModelManager.isStateActive());
    }

    @Test
    void execute_invalidVenueName_failure() {
        PromptBookingVenueCommand command = new PromptBookingVenueCommand(new VenueName("Nonexistent venue"));
        String expectedMessage = MESSAGE_INVALID_VENUE_NAME + PROMPT_MESSAGE_TRY_AGAIN;
        assertCommandFailure(command, model, expectedMessage);

        String state = ModelManager.getState();
        assertTrue(state.equals(STATE_VENUE));
        assertTrue(ModelManager.isStateActive());
    }
}
