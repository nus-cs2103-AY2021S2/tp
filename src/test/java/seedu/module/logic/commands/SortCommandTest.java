package seedu.module.logic.commands;

import static seedu.module.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.module.testutil.TypicalTasks.getSortedTypicalModuleBook;
import static seedu.module.testutil.TypicalTasks.getTypicalModuleBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.UserPrefs;

class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModuleBook(), new UserPrefs());
        expectedModel = new ModelManager(getSortedTypicalModuleBook(), new UserPrefs());
    }

    @Test
    public void execute_sortedTask_showsSameList() {
        assertCommandSuccess(new SortCommand(), expectedModel, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_unsortedTask_showsSortedList() {
        assertCommandSuccess(new SortCommand(), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
