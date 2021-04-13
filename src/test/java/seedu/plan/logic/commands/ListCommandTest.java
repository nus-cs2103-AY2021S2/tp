package seedu.plan.logic.commands;

import static seedu.plan.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plan.testutil.TypicalPlans.getTypicalModulePlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.plan.model.Model;
import seedu.plan.model.ModelManager;
import seedu.plan.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModulePlanner(), new UserPrefs());
        expectedModel = new ModelManager(model.getPlans(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
