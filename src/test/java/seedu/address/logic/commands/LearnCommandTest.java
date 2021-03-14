package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LearnCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class LearnCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_learn_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false, true);
        assertCommandSuccess(new LearnCommand(), model, expectedCommandResult, expectedModel);
    }

}
