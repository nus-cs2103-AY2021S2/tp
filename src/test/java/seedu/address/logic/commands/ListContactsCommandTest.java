package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccessWithUiCommand;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ProjectsFolder;
import seedu.address.model.UserPrefs;
import seedu.address.ui.UiCommand;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListContactsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new ProjectsFolder(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getProjectsFolder(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccessWithUiCommand(new ListContactsCommand(), model, ListContactsCommand.MESSAGE_SUCCESS,
                UiCommand.SHOW_CONTACTS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccessWithUiCommand(new ListContactsCommand(), model, ListContactsCommand.MESSAGE_SUCCESS,
                UiCommand.SHOW_CONTACTS, expectedModel);
    }
}
