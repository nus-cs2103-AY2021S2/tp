package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.ToggleDictionaryOrientationCommand.MESSAGE_TOGGLE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;

/**
 * Toggles the orientation of dictionary panel.
 */
public class ToggleDictionaryOrientationCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_toggle_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_TOGGLE_SUCCESS, UiAction.OPEN,
            UiActionOption.DICTIONARY);
        assertCommandSuccess(new ToggleDictionaryOrientationCommand(), model, expectedCommandResult, expectedModel);
        //value should not equal after toggle
        assertNotEquals(model.getGuiSettings().getDictionaryPanelOrientation(), (
            new ModelManager()).getGuiSettings().getDictionaryPanelOrientation());
        assertCommandSuccess(new ToggleDictionaryOrientationCommand(), model, expectedCommandResult, expectedModel);
        //value should be equal after toggle again
        assertEquals(model.getGuiSettings().getDictionaryPanelOrientation(), (
            new ModelManager()).getGuiSettings().getDictionaryPanelOrientation());
    }
}
