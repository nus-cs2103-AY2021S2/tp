package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteNoteCommand}.
 */
public class MarkAsDoneNoteCommandTest {

    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @BeforeEach
    public void init() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
    }

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());
        assertCommandFailure(new MarkAsDoneNoteCommand(INDEX_FIRST_NOTE), editModeModel,
            Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        MarkAsDoneNoteCommand markAsDoneNoteCommand = new MarkAsDoneNoteCommand(outOfBoundIndex);

        assertCommandFailure(markAsDoneNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkAsDoneNoteCommand markAsDoneNoteCommand = new MarkAsDoneNoteCommand(INDEX_FIRST_CONTACT);
        MarkAsDoneNoteCommand markAsDoneNoteOtherCommand = new MarkAsDoneNoteCommand(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(markAsDoneNoteCommand.equals(markAsDoneNoteCommand));

        // different types -> returns false
        assertFalse(markAsDoneNoteCommand.equals(1));

        // null -> returns false
        assertFalse(markAsDoneNoteCommand.equals(null));

        // different contact -> returns false
        assertFalse(markAsDoneNoteCommand.equals(markAsDoneNoteOtherCommand));
    }

}
