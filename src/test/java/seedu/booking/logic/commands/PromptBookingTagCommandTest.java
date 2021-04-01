package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_START_MESSAGE;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_TAG;
import static seedu.booking.logic.commands.states.AddBookingCommandState.STATE_START;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddBookingCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;
import seedu.booking.model.UserPrefs;

public class PromptBookingTagCommandTest {
    private ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @BeforeEach
    void setup() {
        CommandState commandState = new AddBookingCommandState();
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
        ModelManager.setState(STATE_TAG);

    }

    @Test
    void execute() {
        PromptBookingTagsCommand command = new PromptBookingTagsCommand(new HashSet<Tag>());
        CommandResult expectedResult = new CommandResult(PROMPT_START_MESSAGE);
        CommandResult result;
        try {
            result = command.execute(model);
            assertEquals(expectedResult, result);
        } catch (CommandException ex) {
            ex.printStackTrace();
        }

        String state = ModelManager.getState();
        assertTrue(state.equals(STATE_START));
        assertTrue(ModelManager.isStateActive());
    }
}
