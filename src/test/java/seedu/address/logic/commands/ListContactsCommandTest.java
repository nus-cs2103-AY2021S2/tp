package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.ui.UiCommand;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListContactsCommand.
 */
public class ListContactsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        expectedModel = new ModelManager(model.getColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactsCommand(), model, ListContactsCommand.MESSAGE_SUCCESS,
                UiCommand.SHOW_CONTACTS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListContactsCommand(), model, ListContactsCommand.MESSAGE_SUCCESS,
                UiCommand.SHOW_CONTACTS, expectedModel);
    }
}
