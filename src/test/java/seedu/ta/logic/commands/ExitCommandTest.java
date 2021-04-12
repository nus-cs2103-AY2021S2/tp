package seedu.ta.logic.commands;

import static seedu.ta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ta.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.ta.model.Model;
import seedu.ta.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ExitCommand}.
 */
public class ExitCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
