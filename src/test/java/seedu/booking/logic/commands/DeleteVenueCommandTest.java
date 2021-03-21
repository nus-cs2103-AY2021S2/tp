package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST_VENUE;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.GuiSettings;
import seedu.booking.commons.core.Messages;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.ReadOnlyUserPrefs;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteVenueCommand}.
 */
public class DeleteVenueCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addVenue(VENUE1);
        Venue venueToDelete = model.getFilteredVenueList().get(INDEX_FIRST_VENUE.getZeroBased());
        DeleteVenueCommand deleteVenueCommand = new DeleteVenueCommand(VENUE1);

        String expectedMessage = String.format(DeleteVenueCommand.MESSAGE_DELETE_VENUE_SUCCESS,
                venueToDelete.getVenueName());

        ModelManager expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
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



