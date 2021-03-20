package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.venue.Venue;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteVenueCommand}.
 */
public class DeleteVenueCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());
    /*
    @Test
    public void execute_validIndexUnfilteredList_success() {
        //remove this after we create a stub
        model.addVenue(VENUE1);
        Venue venueToDelete = model.getFilteredVenueList().get(INDEX_FIRST_VENUE.getZeroBased());
        DeleteVenueCommand deleteVenueCommand = new DeleteVenueCommand(VENUE1);

        String expectedMessage = String.format(DeleteVenueCommand.MESSAGE_DELETE_VENUE_SUCCESS,
                venueToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        //remove this after we create a stub
        expectedModel.addVenue(VENUE1);
        expectedModel.deleteVenue(venueToDelete);
        assertCommandSuccess(deleteVenueCommand, model, expectedMessage, expectedModel);
    }
     */

    @Test
    public void execute_invalidVenueUnfilteredList_throwsCommandException() {
        Venue venueNotInSystem = VENUE2;
        DeleteVenueCommand deleteVenueCommand = new DeleteVenueCommand(venueNotInSystem);

        assertCommandFailure(deleteVenueCommand, model, Messages.MESSAGE_INVALID_VENUE_NAME);
    }

    @Test
    public void equals() {
        DeleteVenueCommand deleteFirstCommand = new DeleteVenueCommand(VENUE1);
        DeleteVenueCommand deleteSecondCommand = new DeleteVenueCommand(VENUE2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteVenueCommand deleteFirstCommandCopy = new DeleteVenueCommand(VENUE1);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different venues -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}



