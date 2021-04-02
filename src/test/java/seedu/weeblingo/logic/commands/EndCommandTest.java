package seedu.weeblingo.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.EndCommand.MESSAGE_END_IN_MENU;
import static seedu.weeblingo.logic.commands.EndCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;
//import seedu.weeblingo.model.flashcard.Answer;
//import seedu.weeblingo.testutil.FlashcardBuilder;

public class EndCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_endInQuiz_success() {
        model.getMode().switchModeQuiz();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_endInLearn_success() {
        model.getMode().switchModeLearn();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_endInQuizSession_success() {
        model.getMode().switchModeQuizSession();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_endInCheckSuccess_success() {
        model.getMode().switchModeCheckSuccess();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_endInHistory_success() {
        model.getMode().switchModeHistory();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_endInQuizSessionEnded_success() {
        model.getMode().switchModeQuizSessionEnded();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new EndCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_endInMenu_failure() throws Exception {
        model.getMode().switchModeMenu();
        assertCommandFailure(new EndCommand(), model, MESSAGE_END_IN_MENU);
    }
}
