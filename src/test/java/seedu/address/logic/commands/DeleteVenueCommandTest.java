package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_VENUE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalVenues.VENUE1;
import static seedu.address.testutil.TypicalVenues.VENUE2;
import static seedu.address.testutil.TypicalVenues.VENUE5;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.venue.Venue;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteVenueCommand}.
 */
public class DeleteVenueCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
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
        DeleteVenueCommand deleteFirstCommandCopy = new DeleteVenueCommand(VENUE5);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different venues -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}



