package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.UiCommand;

/**
 * Contains unit tests for {@code ShowParticipantsTabCommand}.
 */
public class ShowParticipantsTabCommandTest {

    @Test
    public void execute_newModelManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ShowParticipantsTabCommand(), model,
                ShowParticipantsTabCommand.MESSAGE_SUCCESS, UiCommand.SHOW_PARTICIPANTS, expectedModel);
    }

}
