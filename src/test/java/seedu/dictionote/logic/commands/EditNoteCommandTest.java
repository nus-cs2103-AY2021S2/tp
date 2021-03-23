package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.DESC_NOTE;
import static seedu.dictionote.logic.commands.CommandTestUtil.DESC_REPLACED_NOTE;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NOTE_CONTENT;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.showNoteAtIndex;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.EditNoteDescriptorBuilder;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());

    @BeforeEach
    public void init() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
    }

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());

        // edit contact in filtered list into a duplicate in contacts list.
        Note noteInList = model.getNoteBook().getNoteList().get(INDEX_SECOND_NOTE.getZeroBased());
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE,
            new EditNoteDescriptorBuilder(noteInList).build());

        assertCommandFailure(editNoteCommand, editModeModel, Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }

    @Test
    public void execute_noteNoteEdited_failure() {
        showNoteAtIndex(model, INDEX_FIRST_NOTE);

        // edit contact in filtered list into a duplicate in contacts list.
        Note noteInList = model.getNoteBook().getNoteList().get(INDEX_SECOND_NOTE.getZeroBased());
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_NOTE,
                new EditNoteDescriptorBuilder(noteInList).build());

        assertCommandFailure(editNoteCommand, model, EditNoteCommand.MESSAGE_NOTHING_CHANGE_NOTE);
    }

    @Test
    public void execute_invalidNoteIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredNoteList().size() + 1);
        EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder().withNote(VALID_NOTE_CONTENT).build();
        EditNoteCommand editNoteCommand = new EditNoteCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditNoteCommand standardCommand = new EditNoteCommand(INDEX_FIRST_NOTE, DESC_NOTE);

        EditNoteCommand.EditNoteDescriptor copyDescriptor = new EditNoteCommand.EditNoteDescriptor(DESC_NOTE);
        EditNoteCommand commandWithSameValues = new EditNoteCommand(INDEX_FIRST_NOTE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_SECOND_NOTE, DESC_REPLACED_NOTE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_FIRST_CONTACT, DESC_REPLACED_NOTE)));

    }

}
