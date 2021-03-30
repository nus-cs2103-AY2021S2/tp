package seedu.dictionote.logic.commands;

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

public class ListNoteCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
        expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                getTypicalNoteBook(), getTypicalDictionary(), getTypicalDefinitionBook());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListNoteCommand(), model, ListNoteCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        //showNoteAtIndex(model, INDEX_FIRST_NOTE); uncomment on v1.3 for search notes using keyword
        assertCommandSuccess(new ListNoteCommand(), model, ListNoteCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
