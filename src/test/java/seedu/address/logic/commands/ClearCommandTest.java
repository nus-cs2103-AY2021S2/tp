package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGarments.getTypicalWardrobe;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.Wardrobe;

public class ClearCommandTest {

    @Test
    public void execute_emptyWardrobe_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyWardrobe_success() {
        Model model = new ModelManager(getTypicalWardrobe(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalWardrobe(), new UserPrefs());
        expectedModel.setWardrobe(new Wardrobe());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
