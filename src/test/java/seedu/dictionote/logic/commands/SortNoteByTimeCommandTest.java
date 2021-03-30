package seedu.dictionote.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;
import static seedu.dictionote.testutil.TypicalContent.getTypicalDictionary;
import static seedu.dictionote.testutil.TypicalDefinition.getTypicalDefinitionBook;
import static seedu.dictionote.testutil.TypicalNotes.getTypicalNoteBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.UserPrefs;

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
    }

    @Test
    public void execute_sort() {
        assertCommandSuccess(new SortNoteCommand(), model,
                SortNoteByTimeCommand.MESSAGE_SORT_NOTE_SUCCESS, expectedModel);
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
