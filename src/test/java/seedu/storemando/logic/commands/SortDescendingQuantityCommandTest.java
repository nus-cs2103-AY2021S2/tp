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

import org.junit.jupiter.api.Test;

import seedu.storemando.commons.core.Messages;
import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.UserPrefs;

public class SortDescendingQuantityCommandTest {

    @Test
    void execute_sortNonEmptyStoreMandoByDescendingQuantity_success() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        expectedModel.setStoreMando(getTypicalStoreMandoSortedByDecreasingQuantity());

        assertCommandSuccess(new SortDescendingQuantityCommand(), model,
            SortDescendingQuantityCommand.MESSAGE_SUCCESS_QUANTITY_DESC, expectedModel);
    }

    @Test
    void execute_sortEmptyStoreMandoByDescendingQuantity_throwsCommandException() {
        Model model = new ModelManager();

        assertCommandFailure(new SortDescendingQuantityCommand(), model,
            Messages.MESSAGE_NO_ITEM_IN_LIST);
    }

    @Test
    void execute_sortSortedStoreMando_success() {
        Model model = new ModelManager(getTypicalStoreMandoSortedByDecreasingQuantity(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMandoSortedByDecreasingQuantity(), new UserPrefs());

        assertCommandSuccess(new SortDescendingQuantityCommand(), model,
            SortDescendingQuantityCommand.MESSAGE_SUCCESS_QUANTITY_DESC, expectedModel);
    }

    @Test
    void execute_sortStoreMandoSortedByExpiryDate_success() {
        Model model = new ModelManager(getTypicalStoreMandoSortedByExpiryDate(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalStoreMandoSortedByDecreasingQuantity(), new UserPrefs());

        assertCommandSuccess(new SortDescendingQuantityCommand(), model,
            SortDescendingQuantityCommand.MESSAGE_SUCCESS_QUANTITY_DESC, expectedModel);
    }

    @Test
    void execute_sortEmptyFilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalStoreMando(), new UserPrefs());
        showEmptyListAfterFind(model, HEATER);

        assertCommandFailure(new SortDescendingQuantityCommand(), model,
            Messages.MESSAGE_NO_ITEM_IN_LIST);
    }

    @Test
    void equals() {
        SortDescendingQuantityCommand standardCommand = new SortDescendingQuantityCommand();

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new SortExpiryDateCommand()));
        assertFalse(standardCommand.equals(new SortAscendingQuantityCommand()));
    }
}
