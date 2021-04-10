package chim.logic.commands;

import static chim.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chim.testutil.TypicalModels.getTypicalChim;

import org.junit.jupiter.api.Test;

import chim.model.Chim;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyChim_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyChim_success() {
        Model model = new ModelManager(getTypicalChim(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalChim(), new UserPrefs());
        expectedModel.setChim(new Chim());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
