package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.ListCommandNoteCommand.SHOWING_LIST_COMMAND_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;

public class ListCommandNoteCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
        assertCommandSuccess(new ListCommandNoteCommand(), model, expectedCommandResult, expectedModel);
    }
}
