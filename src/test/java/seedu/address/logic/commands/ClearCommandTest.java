package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashBack;

import org.junit.jupiter.api.Test;

import seedu.address.model.FlashBack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
