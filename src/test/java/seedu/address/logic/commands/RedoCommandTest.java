package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains unit tests for {@code RedoCommand}.
 */
public class RedoCommandTest {

    @Test
    public void execute_validState_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult commandResult = new CommandResult("feedback", new ShowTodayUiCommand());
        model.commitState(commandResult);
        model.undo();

        assertCommandSuccess(new RedoCommand(), model,
                String.format(RedoCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser()),
                new ShowTodayUiCommand(), expectedModel);
    }

    @Test
    public void execute_invalidState_throwsCommandException() {
        Model model = new ModelManager();
        RedoCommand redoCommand = new RedoCommand();

        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_FAILURE);
    }

}
