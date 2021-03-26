package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class StartCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_start_success() {
        model.getMode().switchModeQuiz();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false, true, false);
        assertCommandSuccess(new StartCommand(), model, expectedCommandResult, expectedModel);
    }
}
