package seedu.budgetBaby.logic.commands;

import static seedu.budgetBaby.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.budgetBaby.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.budgetBaby.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.budgetBaby.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.budgetBaby.model.Model;
import seedu.budgetBaby.model.ModelManager;
import seedu.budgetBaby.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
