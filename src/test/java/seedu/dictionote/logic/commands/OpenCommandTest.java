package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.OpenCommand.SHOWING_OPEN_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;

public class OpenCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_open_success() {
        for (UiActionOption uiActionOption : UiActionOption.values()) {
            CommandResult expectedCommandResult = new CommandResult(SHOWING_OPEN_MESSAGE,
                UiAction.OPEN, uiActionOption);
            assertCommandSuccess(new OpenCommand(uiActionOption), model, expectedCommandResult, expectedModel);
        }
    }
}
