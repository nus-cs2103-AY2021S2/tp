package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowOverviewTabUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains unit tests for {@code ShowOverviewTabCommand}.
 */
public class ShowOverviewTabCommandTest {

    @Test
    public void execute_newModelManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ShowOverviewTabCommand(), model, ShowOverviewTabCommand.MESSAGE_SUCCESS,
                new ShowOverviewTabUiCommand(), expectedModel);
    }

}
