package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.LearnCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class LearnCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_learn_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new LearnCommand(), model, expectedCommandResult, expectedModel);
    }

}
