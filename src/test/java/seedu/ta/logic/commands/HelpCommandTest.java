package seedu.ta.logic.commands;

import static seedu.ta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ta.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code HelpCommand}.
 */
public class HelpCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
