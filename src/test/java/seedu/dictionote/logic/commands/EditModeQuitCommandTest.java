package seedu.dictionote.logic.commands;

import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

public class EditModeQuitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_quitEditMode_failure() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
        assertCommandFailure(new EditModeQuitCommand(), model, EditModeQuitCommand.MESSAGE_NOT_IN_EDIT_MODE);


        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        assertCommandFailure(new EditModeQuitCommand(), model, EditModeQuitCommand.MESSAGE_NOT_IN_EDIT_MODE);

    }

    @Test
    public void execute_quitEditMode_success() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());

        CommandResult expectedCommandResult = new CommandResult(EditModeQuitCommand.MESSAGE_EDIT_MODE_EXIT_SUCCESS,
            UiAction.EDITMODEEXIT);
        assertCommandSuccess(new EditModeQuitCommand(), model, expectedCommandResult, expectedModel);
    }
}
