package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NotifCommand.SHOWING_NOTIF_MESSAGE;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class NotifCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_notif_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_NOTIF_MESSAGE, false,
                true, Optional.empty(), false);
        assertCommandSuccess(new NotifCommand(), model, expectedCommandResult, expectedModel);
    }
}
