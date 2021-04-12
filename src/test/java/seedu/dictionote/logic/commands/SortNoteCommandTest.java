package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.testutil.TypicalNoteContentConfig;

public class SortNoteCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.sortNote();

        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
    }

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());

        assertCommandFailure(new SortNoteCommand(), editModeModel, Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }


    @Test
    public void execute_sort() {
        assertCommandSuccess(new SortNoteCommand(), model,
                SortNoteCommand.MESSAGE_SORT_NOTE_SUCCESS, UiAction.OPEN, UiActionOption.NOTE_LIST, expectedModel);
    }

    @Test
    public void equals() {
        SortNoteCommand sortNoteCommand = new SortNoteCommand();
        SortNoteCommand sortNoteOtherCommand = new SortNoteCommand();

        // same object -> returns true
        assertTrue(sortNoteCommand.equals(sortNoteCommand));

        // different types -> returns false
        assertFalse(sortNoteCommand.equals(22022001));

        // null -> returns false
        assertFalse(sortNoteCommand.equals(null));

        // different contact -> returns true
        assertFalse(sortNoteCommand.equals(sortNoteOtherCommand));
    }
}
