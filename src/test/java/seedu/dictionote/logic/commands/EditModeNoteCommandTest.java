package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

public class EditModeNoteCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_edit_mode_note_failure() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        assertCommandFailure(new EditModeNoteCommand(), model, EditModeNoteCommand.MESSAGE_NO_NOTE_SHOWN);

        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        assertCommandFailure(new EditModeNoteCommand(), model, EditModeNoteCommand.MESSAGE_ALREADY_IN_EDIT_Mode);
    }

    @Test
    public void execute_edit_mode_note_success() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());

        CommandResult expectedCommandResult = new CommandResult(EditModeNoteCommand.MESSAGE_EDIT_MODE_NOTE_SUCCESS,
            UiAction.ENTEREDITMODE);
        assertCommandSuccess(new EditModeNoteCommand(), model, expectedCommandResult, expectedModel);
    }
}
