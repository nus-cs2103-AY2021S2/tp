package seedu.flashback.logic.commands;

import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import org.junit.jupiter.api.Test;

import seedu.flashback.model.FlashBack;
import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFlashBack_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitFlashBack();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFlashBack_success() {
        Model model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFlashBack(), new UserPrefs());
        expectedModel.setFlashBack(new FlashBack());
        expectedModel.commitFlashBack();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
