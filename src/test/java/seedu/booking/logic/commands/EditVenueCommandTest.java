package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_COURT;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_VENUE1;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_VENUE3;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.logic.commands.CommandTestUtil.showVenueAtIndex;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST_VENUE;
import static seedu.booking.testutil.TypicalIndexes.INDEX_SECOND_VENUE;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalVenues.VENUE3;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.EditVenueCommand.EditVenueDescriptor;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;
import seedu.booking.testutil.EditVenueDescriptorBuilder;
import seedu.booking.testutil.VenueBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditVenueCommand.
 */
public class EditVenueCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Venue editedVenue = new VenueBuilder().build();
        model.addVenue(VENUE1);
        EditVenueDescriptor descriptor = new EditVenueDescriptorBuilder(editedVenue).build();
        EditVenueCommand editVenueCommand = new EditVenueCommand(new VenueName(VALID_VENUE_NAME_VENUE1), descriptor);

        String expectedMessage = String.format(EditVenueCommand.MESSAGE_EDIT_VENUE_SUCCESS, editedVenue);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setVenue(model.getFilteredVenueList().get(0), editedVenue);

        assertCommandSuccess(editVenueCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        model.addVenue(VENUE1);
        EditVenueCommand editVenueCommand =
                new EditVenueCommand(new VenueName(VALID_VENUE_NAME_VENUE1), new EditVenueDescriptor());
        Venue editedVenue = model.getFilteredVenueList().get(INDEX_FIRST_VENUE.getZeroBased());

        String expectedMessage = String.format(EditVenueCommand.MESSAGE_EDIT_VENUE_SUCCESS, editedVenue);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());

        assertCommandSuccess(editVenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model.addVenue(VENUE1);
        showVenueAtIndex(model, INDEX_FIRST_VENUE);

        Venue venueInFilteredList = model.getFilteredVenueList().get(INDEX_FIRST_VENUE.getZeroBased());
        Venue editedVenue = new VenueBuilder(venueInFilteredList).withName(VALID_VENUE_NAME_HALL).build();
        EditVenueCommand editVenueCommand = new EditVenueCommand(new VenueName(VALID_VENUE_NAME_VENUE1),
                new EditVenueDescriptorBuilder().withVenueName(VALID_VENUE_NAME_HALL).build());

        String expectedMessage = String.format(EditVenueCommand.MESSAGE_EDIT_VENUE_SUCCESS, editedVenue);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setVenue(model.getFilteredVenueList().get(0), editedVenue);

        assertCommandSuccess(editVenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        model.addVenue(VENUE1);
        model.addVenue(VENUE3);
        Venue firstVenue = model.getFilteredVenueList().get(INDEX_FIRST_VENUE.getZeroBased());
        EditVenueDescriptor descriptor = new EditVenueDescriptorBuilder(firstVenue).build();
        EditVenueCommand editVenueCommand = new EditVenueCommand(new VenueName(VALID_VENUE_NAME_VENUE3), descriptor);

        assertCommandFailure(editVenueCommand, model, EditVenueCommand.MESSAGE_DUPLICATE_VENUE);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        model.addVenue(VENUE1);
        model.addVenue(VENUE3);
        showVenueAtIndex(model, INDEX_FIRST_VENUE);

        // edit venue in filtered list into a duplicate in the booking system
        Venue venueInList = model.getBookingSystem().getVenueList().get(INDEX_SECOND_VENUE.getZeroBased());
        EditVenueCommand editVenueCommand = new EditVenueCommand(new VenueName(VALID_VENUE_NAME_VENUE1),
                new EditVenueDescriptorBuilder(venueInList).build());

        assertCommandFailure(editVenueCommand, model, EditVenueCommand.MESSAGE_DUPLICATE_VENUE);
    }

    @Test
    public void equals() {
        EditVenueDescriptor descriptor = new EditVenueDescriptorBuilder(VENUE1).build();
        final EditVenueCommand standardCommand =
                new EditVenueCommand(new VenueName(VALID_VENUE_NAME_COURT), descriptor);

        // same values -> returns true
        EditVenueDescriptor copyDescriptor = new EditVenueDescriptor(descriptor);
        EditVenueCommand commandWithSameValues =
                new EditVenueCommand(new VenueName(VALID_VENUE_NAME_COURT), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different venues -> returns false
        assertFalse(standardCommand.equals(new EditVenueCommand(new VenueName(VALID_VENUE_NAME_HALL),
                descriptor)));

        // different descriptor -> returns false
        EditVenueDescriptor descriptorNew = new EditVenueDescriptorBuilder(VENUE3).build();
        assertFalse(standardCommand.equals(new EditVenueCommand(new VenueName(VALID_VENUE_NAME_HALL),
                descriptorNew)));
    }

}
