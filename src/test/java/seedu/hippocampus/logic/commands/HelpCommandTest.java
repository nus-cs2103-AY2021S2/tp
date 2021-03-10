package seedu.hippocampus.logic.commands;

import static seedu.hippocampus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hippocampus.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.hippocampus.model.Model;
import seedu.hippocampus.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
