package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowTodosTabUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains unit tests for {@code ShowTodosTabCommand}.
 */
public class ShowTodosTabCommandTest {

    @Test
    public void execute_newModelManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ShowTodosTabCommand(), model, ShowTodosTabCommand.MESSAGE_SUCCESS,
                new ShowTodosTabUiCommand(), expectedModel);
    }

}
