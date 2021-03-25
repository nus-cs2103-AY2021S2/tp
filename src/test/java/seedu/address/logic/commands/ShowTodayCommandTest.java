package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.uicommands.ShowTodayUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains unit tests for {@code ShowTodayCommand}.
 */
public class ShowTodayCommandTest {

    @Test
    public void execute_newModelManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ShowTodayCommand(), model, ShowTodayCommand.MESSAGE_SUCCESS,
                new ShowTodayUiCommand(), expectedModel);
    }

}
