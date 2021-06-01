package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains unit tests for {@code UndoCommand}.
 */
public class UndoCommandTest {

    @Test
    public void execute_validState_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult commandResult = new CommandResult("feedback", new ShowTodayUiCommand());
        model.commitState(commandResult);
        model.commitState(commandResult);

        assertCommandSuccess(new UndoCommand(), model,
                String.format(UndoCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser()),
                new ShowTodayUiCommand(), expectedModel);

        assertCommandSuccess(new UndoCommand(), model,
                String.format(UndoCommand.MESSAGE_SUCCESS, "Viewing Initial State."),
                new ShowTodayUiCommand(), expectedModel);
    }

    @Test
    public void execute_invalidState_throwsCommandException() {
        Model model = new ModelManager();
        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_FAILURE);
    }

}
