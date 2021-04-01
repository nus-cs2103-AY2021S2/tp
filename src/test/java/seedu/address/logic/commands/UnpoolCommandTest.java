package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PASSENGERS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPools.getTypicalAddressBookPools;
import static seedu.address.testutil.TypicalPassengers.ALICE;
import static seedu.address.testutil.TypicalPassengers.BOB;
import static seedu.address.testutil.TypicalPassengers.CARL;
import static seedu.address.testutil.TypicalPools.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.pool.Pool;

import java.util.ArrayList;
import java.util.List;

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

        String passengerNames = ALICE.getName() + ", " + BOB.getName() + ", " + CARL.getName();
        String expectedMessage = String.format(UnpoolCommand.MESSAGE_UNPOOL_SUCCESS, passengerNames);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePool(poolToRemove);

        assertCommandSuccess(unpoolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPoolList().size() + 1);
        UnpoolCommand unpoolCommand = new UnpoolCommand(outOfBoundIndex);

        assertCommandFailure(unpoolCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception{
        Model expectedModel = new ModelManager(getTypicalAddressBookPools(), new UserPrefs());
        List<String> searchString = new ArrayList<>();
        searchString.add("Alice");

        model.updateFilteredPassengerList(new NameContainsKeywordsPredicate(searchString));
        Pool poolToRemove = model.getFilteredPoolList().get(INDEX_FIRST.getZeroBased());
        UnpoolCommand unpoolCommand = new UnpoolCommand(INDEX_FIRST);
        String expectedMessage = String.format(UnpoolCommand.MESSAGE_UNPOOL_SUCCESS, poolToRemove);

        expectedModel.deletePool(poolToRemove);
        expectedModel.updateFilteredPassengerList(PREDICATE_SHOW_ALL_PASSENGERS);

        assertCommandSuccess(unpoolCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPassengerList().size());

        UnpoolCommand unpoolCommand = new UnpoolCommand(outOfBoundIndex);

        assertCommandFailure(unpoolCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
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
    private void showNoPassenger(Model model) {
        model.updateFilteredPoolList(p -> false);

        assertTrue(model.getFilteredPoolList().isEmpty());
    }
}
