package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.logic.commands.ToggleNoteOrientationCommand.MESSAGE_TOGGLE_SUCCESS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

public class ToggleNoteOrientationCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @BeforeEach
    public void init() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
    }

    @Test
    public void execute_toggle_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_TOGGLE_SUCCESS, UiAction.OPEN,
            UiActionOption.NOTE_CONTENT);
        assertCommandSuccess(new ToggleNoteOrientationCommand(), model, expectedCommandResult, expectedModel);
        //value should not equal after toggle
        assertNotEquals(model.getGuiSettings().getNotePanelOrientation(),
            (new ModelManager()).getGuiSettings().getNotePanelOrientation());
        assertCommandSuccess(new ToggleNoteOrientationCommand(), model, expectedCommandResult, expectedModel);
        //value should be equal after toggle again
        assertEquals(model.getGuiSettings().getNotePanelOrientation(),
            (new ModelManager()).getGuiSettings().getNotePanelOrientation());
    }
}
