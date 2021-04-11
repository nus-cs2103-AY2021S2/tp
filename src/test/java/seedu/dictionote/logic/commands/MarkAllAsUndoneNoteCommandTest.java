package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

public class MarkAllAsUndoneNoteCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        assertCommandFailure(new MarkAllAsUndoneNoteCommand(), editModeModel,
            Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }


    @Test
    public void equals() {
        MarkAllAsUndoneNoteCommand markAllAsUndoneNoteCommand = new MarkAllAsUndoneNoteCommand();
        MarkAllAsUndoneNoteCommand markAllAsUndoneNoteOtherCommand = new MarkAllAsUndoneNoteCommand();

        // same object -> returns true
        assertTrue(markAllAsUndoneNoteCommand.equals(markAllAsUndoneNoteCommand));

        // different types -> returns false
        assertFalse(markAllAsUndoneNoteCommand.equals(1));

        // null -> returns false
        assertFalse(markAllAsUndoneNoteCommand.equals(null));

        // different contact -> returns false
        assertTrue(markAllAsUndoneNoteCommand.equals(markAllAsUndoneNoteOtherCommand));
    }
}
