package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_DESC;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_TAG;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.Description;

public class PromptBookingDescCommandTest {
    private ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @BeforeEach
    void setup() {
        CommandState commandState = new AddBookingCommandState();
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
        ModelManager.setState(STATE_DESC);
    }

    @Test
    void execute() {
        PromptBookingDescCommand command = new PromptBookingDescCommand(new Description("No description provided."));
        CommandResult expectedResult = new CommandResult(PROMPT_TAG_MESSAGE);
        CommandResult result;
        try {
            result = command.execute(model);
            assertEquals(expectedResult, result);
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.");
        }

        String state = ModelManager.getState();
        System.out.println(state);
        assertTrue(state.equals(STATE_TAG));
        assertTrue(ModelManager.isStateActive());
    }
}
