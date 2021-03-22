package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReviewCommand.MESSAGE_REVIEW_FAIL;
import static seedu.address.logic.commands.ReviewCommand.MESSAGE_REVIEW_SUCCESS;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ReviewCommandTest {
    private Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    @Test
    public void execute_review_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_REVIEW_SUCCESS, false, false, true);
        assertCommandSuccess(new ReviewCommand(), model, expectedCommandResult, expectedModel);
    }
    @Test
    public void execute_emptyFlashBack_throwsCommandException() {
        Model emptyModel = new ModelManager();
        assertCommandFailure(new ReviewCommand(), emptyModel, MESSAGE_REVIEW_FAIL);
    }
}
