package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyContactList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS,
                new ShowTodayUiCommand(), expectedModel);
    }

    @Test
    public void execute_nonEmptyContactList_success() {
        Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        expectedModel.setColabFolder(new ColabFolder());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS,
                new ShowTodayUiCommand(), expectedModel);
    }

}
