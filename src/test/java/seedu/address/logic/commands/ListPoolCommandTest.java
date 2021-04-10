package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.prepareNamePredicate;
import static seedu.address.testutil.TypicalPools.getTypicalAddressBookPools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPoolCommand.
 */
public class ListPoolCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookPools(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_poolListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPoolCommand(), model, ListPoolCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_poolListIsFiltered_showsEverything() {
        PooledPassengerContainsKeywordsPredicate predicate = prepareNamePredicate("Alice Bob Daniel");
        expectedModel.updateFilteredPoolList(predicate);
        assertCommandSuccess(new ListPoolCommand(), model, ListPoolCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
