package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalBookings.BOOKING1;
import static seedu.booking.testutil.TypicalBookings.BOOKING2;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.Booking;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteBookingCommand}.
 */
public class DeleteBookingCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addBooking(BOOKING1);
        Booking bookingToDelete = model.getFilteredBookingList().get(INDEX_FIRST_BOOKING.getZeroBased());
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(BOOKING1.getId());

        String expectedMessage = String.format(DeleteBookingCommand.MESSAGE_DELETE_BOOKING_SUCCESS,
                bookingToDelete.getId().toString());

        ModelManager expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
        expectedModel.deleteBooking(bookingToDelete.getId());
        assertCommandSuccess(deleteBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidVenueUnfilteredList_throwsCommandException() {
        Booking bookingNotInSystem = BOOKING2;
        DeleteBookingCommand deleteBookingCommand = new DeleteBookingCommand(bookingNotInSystem.getId());

        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_ID);
    }

    @Test
    public void equals() {
        DeleteBookingCommand deleteFirstCommand = new DeleteBookingCommand(BOOKING1.getId());
        DeleteBookingCommand deleteSecondCommand = new DeleteBookingCommand(BOOKING2.getId());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBookingCommand deleteFirstCommandCopy = new DeleteBookingCommand(BOOKING1.getId());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different venues -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
