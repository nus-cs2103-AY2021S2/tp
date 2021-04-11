package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPassengers.BENSON;
import static seedu.address.testutil.TypicalPassengers.CARL;
import static seedu.address.testutil.TypicalPassengers.DANIEL;
import static seedu.address.testutil.TypicalPassengers.ELLE;
import static seedu.address.testutil.TypicalPassengers.FIONA;
import static seedu.address.testutil.TypicalPools.getTypicalAddressBookPools;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pool.Pool;
import seedu.address.model.pool.PooledPassengerContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnpoolCommandTest}.
 */
public class UnpoolCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookPools(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Pool poolToRemove = model.getFilteredPoolList().get(INDEX_FIRST.getZeroBased());
        UnpoolCommand unpoolCommand = new UnpoolCommand(INDEX_FIRST);

        String passengerNames = ALICE.getName() + ", " + BENSON.getName() + ", " + CARL.getName();
        String expectedMessage = String.format(UnpoolCommand.MESSAGE_UNPOOL_SUCCESS, passengerNames);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePool(poolToRemove);

        assertCommandSuccess(unpoolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPoolList().size() + 1);
        UnpoolCommand unpoolCommand = new UnpoolCommand(outOfBoundIndex);

        assertCommandFailure(unpoolCommand, model, Messages.MESSAGE_INVALID_POOL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBookPools(), new UserPrefs());
        List<String> searchString = new ArrayList<>();
        searchString.add("Daniel");

        model.updateFilteredPoolList(new PooledPassengerContainsKeywordsPredicate(searchString));
        Pool poolToRemove = model.getFilteredPoolList().get(INDEX_FIRST.getZeroBased());
        UnpoolCommand unpoolCommand = new UnpoolCommand(INDEX_FIRST);
        String passengerNames = DANIEL.getName() + ", " + ELLE.getName() + ", " + FIONA.getName();
        String expectedMessage = String.format(UnpoolCommand.MESSAGE_UNPOOL_SUCCESS, passengerNames);

        expectedModel.deletePool(poolToRemove);
        showNoPools(expectedModel);

        assertCommandSuccess(unpoolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Index outOfBoundIndex = INDEX_THIRD;
        List<String> searchString = new ArrayList<>();
        searchString.add("Alice");

        model.updateFilteredPoolList(new PooledPassengerContainsKeywordsPredicate(searchString));

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPoolList().size());

        UnpoolCommand unpoolCommand = new UnpoolCommand(outOfBoundIndex);

        assertCommandFailure(unpoolCommand, model, Messages.MESSAGE_INVALID_POOL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnpoolCommand unpoolFirstCommand = new UnpoolCommand(INDEX_FIRST);
        UnpoolCommand unpoolSecondCommand = new UnpoolCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(unpoolFirstCommand.equals(unpoolFirstCommand));

        // same values -> returns true
        UnpoolCommand unpoolFirstCommandCopy = new UnpoolCommand(INDEX_FIRST);
        assertTrue(unpoolFirstCommand.equals(unpoolFirstCommandCopy));

        // different types -> returns false
        assertFalse(unpoolFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unpoolFirstCommand.equals(null));

        // different passenger -> returns false
        assertFalse(unpoolFirstCommand.equals(unpoolSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPools(Model model) {
        model.updateFilteredPoolList(p -> false);

        assertTrue(model.getFilteredPoolList().isEmpty());
    }
}
