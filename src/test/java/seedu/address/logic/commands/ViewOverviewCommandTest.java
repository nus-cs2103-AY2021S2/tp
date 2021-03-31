package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowOverviewTabUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains unit tests for {@code ViewOverviewCommand}.
 */
public class ViewOverviewCommandTest {

    @Test
    public void execute_newModelManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ViewOverviewCommand(), model, ViewOverviewCommand.MESSAGE_SUCCESS,
                new ShowOverviewTabUiCommand(), expectedModel);
    }

}
