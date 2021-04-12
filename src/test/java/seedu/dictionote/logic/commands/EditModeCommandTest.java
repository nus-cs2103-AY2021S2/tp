package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

public class EditModeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_editMode_failure() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        assertCommandFailure(new EditModeCommand(), model, EditModeCommand.MESSAGE_USAGE);

        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        assertCommandFailure(new EditModeCommand(), model, EditModeCommand.MESSAGE_ALREADY_IN_EDIT_MODE);
    }

    @Test
    public void execute_editMode_success() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());

        CommandResult expectedCommandResult = new CommandResult(EditModeCommand.MESSAGE_EDIT_MODE_NOTE_SUCCESS,
            UiAction.EDITMODEENTER);
        assertCommandSuccess(new EditModeCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equal() {
        EditModeCommand editModeCommand1 = new EditModeCommand();
        EditModeCommand editModeCommand2 = new EditModeCommand();

        // same object -> returns true
        assertTrue(editModeCommand1.equals(editModeCommand1));

        // same values -> returns true
        assertTrue(editModeCommand1.equals(editModeCommand2));

        // different types -> returns false
        assertFalse(editModeCommand1.equals(1));

        // null -> returns false
        assertFalse(editModeCommand1.equals(null));
    }
}
