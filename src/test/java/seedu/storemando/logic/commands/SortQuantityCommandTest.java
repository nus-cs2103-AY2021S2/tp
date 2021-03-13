package seedu.storemando.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.storemando.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMando;
import static seedu.storemando.testutil.TypicalItems.getTypicalStoreMandoSortedByQuantity;

import org.junit.jupiter.api.Test;

import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;

class SortQuantityCommandTest {

    @Test
    void execute_sortNonEmptyStoreMandoByQuantity_success() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel.setStoreMando(getTypicalStoreMandoSortedByQuantity());

        assertCommandSuccess(new SortQuantityCommand(), model,
            SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    void execute_sortEmptyStoreMandoByQuantity_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new SortQuantityCommand(), model,
            SortCommand.MESSAGE_NO_ITEMS_TO_SORT);
    }

    @Test
    void equals() {
        final SortQuantityCommand standardCommand = new SortQuantityCommand();

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new SortExpiryDateCommand()));
    }
}