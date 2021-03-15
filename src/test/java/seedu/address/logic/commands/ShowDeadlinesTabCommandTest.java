package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.UiCommand;

/**
 * Contains unit tests for {@code ShowDeadlinesTabCommand}.
 */
public class ShowDeadlinesTabCommandTest {

    @Test
    public void execute_newModelManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ShowDeadlinesTabCommand(), model, ShowDeadlinesTabCommand.MESSAGE_SUCCESS,
                UiCommand.SHOW_DEADLINES, expectedModel);
    }

}
