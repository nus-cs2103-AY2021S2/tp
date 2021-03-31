package seedu.smartlib.logic.commands;

import static seedu.smartlib.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.smartlib.testutil.TypicalModels.getTypicalSmartLib;

import org.junit.jupiter.api.Test;

import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptySmartLib_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySmartLib_success() {
        Model model = new ModelManager(getTypicalSmartLib(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSmartLib(), new UserPrefs());
        expectedModel.setSmartLib(new SmartLib());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
