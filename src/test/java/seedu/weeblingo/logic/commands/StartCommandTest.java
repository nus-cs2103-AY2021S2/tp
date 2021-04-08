package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_INTEGER_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_START_TAGS_SET_GENERIC;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_NOT_IN_QUIZ_MODE;
import static seedu.weeblingo.logic.commands.StartCommand.MESSAGE_SUCCESS;

import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;
import seedu.weeblingo.model.tag.Tag;

import org.junit.jupiter.api.Test;


import java.util.Set;

public class StartCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_start_success() {
        model.getMode().switchModeQuiz();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SUCCESS, false, false);
        Set<Tag> tags = VALID_START_TAGS_SET_GENERIC;
        tags.add(new Tag("hiragana"));
        assertCommandSuccess(new StartCommand(VALID_START_INTEGER_GENERIC, tags),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_startInMenu_failure() {
        model.getMode().switchModeMenu();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInLearn_failure() {
        model.getMode().switchModeLearn();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInQuizSession_failure() {
        model.getMode().switchModeQuizSession();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInCheckSuccess_failure() {
        model.getMode().switchModeCheckSuccess();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInHistory_failure() {
        model.getMode().switchModeHistory();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

    @Test
    public void execute_startInQuizSessionEnded_failure() {
        model.getMode().switchModeQuizSessionEnded();
        assertCommandFailure(new StartCommand(VALID_START_INTEGER_GENERIC, VALID_START_TAGS_SET_GENERIC),
                model, MESSAGE_NOT_IN_QUIZ_MODE);
    }

}
