package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_IN_QUIZ_SESSION;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_NOT_IN_QUIZ_MODE;
//import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;

public class StartCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    //@Test
    //public void execute_startInQuiz_success() throws CommandException {
    //    model.getMode().switchModeQuiz();
    //    CommandResult expectedCommandResult = new CommandResult(
    //            MESSAGE_SUCCESS, false, false);
    //    assertCommandSuccess(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
    //            model, expectedCommandResult, expectedModel);
    //}

    @Test
    public void execute_startInMenu_failure() {
        model.switchModeMenu();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInLearn_failure() throws CommandException {
        model.getMode().switchModeLearn();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInQuizSession_failure() {
        model.switchModeQuizSession();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_IN_QUIZ_SESSION);
    }

    @Test
    public void execute_startInCheckSuccess_failure() {
        model.switchModeCheckSuccess();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_IN_QUIZ_SESSION);
    }

    @Test
    public void execute_startInHistory_failure() {
        model.switchModeHistory();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    //@Test
    //public void execute_startInQuizSessionEnded_success() throws CommandException {
    //    model.getMode().switchModeQuizSessionEnded();
    //    CommandResult expectedCommandResult = new CommandResult(
    //            MESSAGE_SUCCESS, false, false);
    //    assertCommandSuccess(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
    //            model, expectedCommandResult, expectedModel);
    //}

}
