package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.logic.commands.CommandTestUtil.showEmptyListAfterFind;
import static seedu.storemando.testutil.TypicalItems.HEATER;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMandoSortedByDecreasingQuantity;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMandoSortedByExpiryDate;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMandoSortedByIncreasingQuantity;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;

class SortQuantityCommandTest {

    @Test
    void execute_sortNonEmptyStoreMandoByIncreasingQuantity_success() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel.setStoreMando(getTypicalStoreMandoSortedByIncreasingQuantity());

        assertCommandSuccess(new SortQuantityCommand(true), model,
            SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_sortNonEmptyStoreMandoByDecreasingQuantity_success() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel.setStoreMando(getTypicalStoreMandoSortedByDecreasingQuantity());

        assertCommandSuccess(new SortQuantityCommand(false), model,
            SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_sortEmptyStoreMandoByQuantity_throwsCommandException() {
        Model model = new ModelManager();

        assertCommandFailure(new SortQuantityCommand(true), model,
            SortCommand.MESSAGE_NO_ITEMS_TO_SORT);
    }

    @Test
    void execute_sortSortedStoreMando_success() {
        Model model = new ModelManager(getTypicalStoreMandoSortedByIncreasingQuantity(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMandoSortedByIncreasingQuantity(), new UserPrefs());

        assertCommandSuccess(new SortQuantityCommand(true), model,
            SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_sortStoreMandoSortedByExpiryDate_success() {
        Model model = new ModelManager(getTypicalStoreMandoSortedByExpiryDate(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMandoSortedByIncreasingQuantity(), new UserPrefs());

        assertCommandSuccess(new SortQuantityCommand(true), model,
            SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_sortEmptyFilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        showEmptyListAfterFind(model, HEATER);

        assertCommandFailure(new SortQuantityCommand(true), model,
            SortCommand.MESSAGE_NO_ITEMS_TO_SORT);
    }

    @Test
    void equals() {
        final SortQuantityCommand standardCommand = new SortQuantityCommand(true);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new SortExpiryDateCommand()));
    }
}
