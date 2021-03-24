package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;

public class MarkAsUndoneNoteCommandTest {
    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        MarkAsUndoneNoteCommand markAsUndoneNoteCommand = new MarkAsUndoneNoteCommand(outOfBoundIndex);

        assertCommandFailure(markAsUndoneNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkAsUndoneNoteCommand markAsUndoneNoteCommand = new MarkAsUndoneNoteCommand(INDEX_FIRST_CONTACT);
        MarkAsUndoneNoteCommand markAsUndoneNoteOtherCommand = new MarkAsUndoneNoteCommand(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(markAsUndoneNoteCommand.equals(markAsUndoneNoteCommand));

        // different types -> returns false
        assertFalse(markAsUndoneNoteCommand.equals(1));

        // null -> returns false
        assertFalse(markAsUndoneNoteCommand.equals(null));

        // different contact -> returns false
        assertFalse(markAsUndoneNoteCommand.equals(markAsUndoneNoteOtherCommand));
    }
}
