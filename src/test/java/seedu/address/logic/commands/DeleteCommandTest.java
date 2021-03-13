package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPassengerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PASSENGER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PASSENGER;
import static seedu.address.testutil.TypicalPassengers.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.passenger.Passenger;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Passenger passengerToDelete = model.getFilteredPassengerList().get(INDEX_FIRST_PASSENGER.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PASSENGER);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PASSENGER_SUCCESS, passengerToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePassenger(passengerToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPassengerList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);

        Passenger passengerToDelete = model.getFilteredPassengerList().get(INDEX_FIRST_PASSENGER.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PASSENGER);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PASSENGER_SUCCESS, passengerToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePassenger(passengerToDelete);
        showNoPassenger(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPassengerAtIndex(model, INDEX_FIRST_PASSENGER);

        Index outOfBoundIndex = INDEX_SECOND_PASSENGER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPassengerList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PASSENGER);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PASSENGER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PASSENGER);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different passenger -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPassenger(Model model) {
        model.updateFilteredPassengerList(p -> false);

        assertTrue(model.getFilteredPassengerList().isEmpty());
    }
}
