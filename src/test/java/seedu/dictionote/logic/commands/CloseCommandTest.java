package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CloseCommand.SHOWING_CLOSE_MESSAGE;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;

public class CloseCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_close_success() {
        for (UiActionOption uiActionOption : UiActionOption.values()) {
            CommandResult expectedCommandResult = new CommandResult(SHOWING_CLOSE_MESSAGE,
                UiAction.CLOSE, uiActionOption);
            assertCommandSuccess(new CloseCommand(uiActionOption), model, expectedCommandResult, expectedModel);
        }
    }
}
