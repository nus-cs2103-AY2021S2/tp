package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.ListCommandContactCommand.SHOWING_LIST_COMMAND_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;

public class ListCommandContactCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_LIST_COMMAND_MESSAGE);
        assertCommandSuccess(new ListCommandContactCommand(), model, expectedCommandResult, expectedModel);
    }
}
