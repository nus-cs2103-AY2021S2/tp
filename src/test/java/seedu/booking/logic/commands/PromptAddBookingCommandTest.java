package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_EMAIL_MESSAGE;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_EMAIL;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.Test;

import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

public class PromptAddBookingCommandTest {
    private ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    void execute() {
        CommandResult expectedResult = new CommandResult(PROMPT_EMAIL_MESSAGE);
        CommandResult result = new PromptAddBookingCommand().execute(model);
        assertEquals(expectedResult, result);

        String state = ModelManager.getState();
        assertTrue(state.equals(STATE_EMAIL));
        assertTrue(ModelManager.isStateActive());

        ModelManager.resetCommandState();
    }
}
