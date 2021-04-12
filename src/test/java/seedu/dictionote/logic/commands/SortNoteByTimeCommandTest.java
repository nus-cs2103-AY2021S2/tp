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

public class SortNoteByTimeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel.sortNoteByTime();

        model.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
        expectedModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigWitouthNote());
    }

    @Test
    public void execute_onEditMode_fail() {
        Model editModeModel = new ModelManager();
        editModeModel.setNoteContentConfig(TypicalNoteContentConfig.getTypicalNoteContentConfigEditMode());

        assertCommandFailure(new SortNoteByTimeCommand(), editModeModel, Messages.MESSAGE_COMMAND_DISABLE_ON_EDIT_MODE);
    }

    @Test
    public void execute_sort() {
        assertCommandSuccess(new SortNoteByTimeCommand(), model, SortNoteByTimeCommand.MESSAGE_SORT_NOTE_SUCCESS,
            UiAction.OPEN, UiActionOption.NOTE_LIST, expectedModel);
    }

    @Test
    public void equals() {
        SortNoteByTimeCommand sortNoteByTimeCommand = new SortNoteByTimeCommand();
        SortNoteByTimeCommand sortNoteByTimeOtherCommand = new SortNoteByTimeCommand();

        // same object -> returns true
        assertTrue(sortNoteByTimeCommand.equals(sortNoteByTimeCommand));

        // different types -> returns false
        assertFalse(sortNoteByTimeCommand.equals("tree"));

        // null -> returns false
        assertFalse(sortNoteByTimeCommand.equals(null));

        // different object return false
        assertFalse(sortNoteByTimeCommand.equals(sortNoteByTimeOtherCommand));
    }
}
