package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.NextCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class NextCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_next_success() {
        model.startQuiz();
        model.getMode().switchModeQuizSession();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false, true);
        assertCommandSuccess(new NextCommand(), model, expectedCommandResult, expectedModel);
    }
}
