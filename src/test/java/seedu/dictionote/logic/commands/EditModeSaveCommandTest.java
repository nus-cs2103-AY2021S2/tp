package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditModeSaveCommandTest {

    private Model model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
        getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    private Model expectedModel = new ModelManager(getTypicalContactsList(), new UserPrefs(),
        getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());


    @Test
    public void execute_saveEditMode_failure() {
        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWithNote());
        assertCommandFailure(new EditModeSaveCommand(), model, EditModeSaveCommand.MESSAGE_NOT_IN_EDIT_MODE);

        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        assertCommandFailure(new EditModeSaveCommand(), model, EditModeSaveCommand.MESSAGE_NOT_IN_EDIT_MODE);

    }

    @Test
    public void equal() {
        EditModeSaveCommand editModeSaveCommand1 = new EditModeSaveCommand();
        EditModeSaveCommand editModeSaveCommand2 = new EditModeSaveCommand();

        // same object -> returns true
        assertTrue(editModeSaveCommand1.equals(editModeSaveCommand1));

        // same values -> returns true
        assertTrue(editModeSaveCommand1.equals(editModeSaveCommand2));

        // different types -> returns false
        assertFalse(editModeSaveCommand1.equals(1));

        // null -> returns false
        assertFalse(editModeSaveCommand1.equals(null));
    }

}
