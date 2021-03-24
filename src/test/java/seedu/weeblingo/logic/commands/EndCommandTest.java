package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.EndCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class EndCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_end_success() {
        model.getMode().switchModeLearn();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }
}
