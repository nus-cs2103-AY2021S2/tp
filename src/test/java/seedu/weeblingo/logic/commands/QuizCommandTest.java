package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.QuizCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class QuizCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_quiz_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false, true, showAnswer);
        assertCommandSuccess(new QuizCommand(), model, expectedCommandResult, expectedModel);
    }
}
