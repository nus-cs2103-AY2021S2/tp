package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOKING1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOKING2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BOOKING_END;
import static seedu.address.logic.commands.CommandTestUtil.OVERLAP_BOOKING_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOKING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOKING2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showResidenceAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_RESIDENCE;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.testutil.BookingBuilder;
import seedu.address.testutil.EditBookingDescriptorBuilder;

public class EditBookingCommandTest {

    private Model model = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());

    @Test
    public void execute_validResidenceIndexInvalidBookingIndexFilteredList_failure() {
        showResidenceAtIndex(model, INDEX_FIRST_RESIDENCE);
        Index outOfBoundBookingIndex = INDEX_FIRST_BOOKING;
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_FIRST_RESIDENCE, outOfBoundBookingIndex,
                new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING1).build());
        assertCommandFailure(editBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidResidenceIndexValidBookingIndexFilteredList_failure() {
        showResidenceAtIndex(model, INDEX_SECOND_RESIDENCE);
        Index outOfBoundResidenceIndex = INDEX_THIRD_RESIDENCE;
        assertTrue(outOfBoundResidenceIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());
        Index validBookingIndex = INDEX_FIRST_BOOKING;
        assertTrue(outOfBoundResidenceIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());
        EditBookingCommand editBookingCommand = new EditBookingCommand(INDEX_THIRD_RESIDENCE, validBookingIndex,
                new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING1).build());
        assertCommandFailure(editBookingCommand, model, Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);

    }

    @Test
    public void execute_validResidenceAndBookingIndex_invalidEndDate() {
        Index validResidenceIndex = INDEX_SECOND_RESIDENCE;
        Index validBookingIndex = INDEX_FIRST_BOOKING;
        Residence residenceToEdit = model.getFilteredResidenceList().get(validResidenceIndex.getZeroBased());
        BookingList bookingListToEdit = residenceToEdit.getBookingList();
        Booking bookingToEdit = bookingListToEdit.getBooking(validBookingIndex.getZeroBased());
        Booking editedBooking = new BookingBuilder(bookingToEdit).withEnd(INVALID_BOOKING_END).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(validResidenceIndex, validBookingIndex,
                new EditBookingDescriptorBuilder().withEndDate(INVALID_BOOKING_END).build());
        String expectedMessage = EditBookingCommand.MESSAGE_NOT_VALID_START_DATE;
        assertCommandFailure(editBookingCommand, model, expectedMessage);
    }

    @Test
    public void execute_validResidenceAndBookingIndex_overlapDate() {
        Index validResidenceIndex = INDEX_SECOND_RESIDENCE;
        Index validBookingIndex = INDEX_SECOND_BOOKING;
        Residence residenceToEdit = model.getFilteredResidenceList().get(validResidenceIndex.getZeroBased());
        BookingList bookingListToEdit = residenceToEdit.getBookingList();
        Booking bookingToEdit = bookingListToEdit.getBooking(validBookingIndex.getZeroBased());
        Booking editedBooking = new BookingBuilder(bookingToEdit).withStart(OVERLAP_BOOKING_DATE).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(validResidenceIndex, validBookingIndex,
                new EditBookingDescriptorBuilder().withStartDate(OVERLAP_BOOKING_DATE).build());
        String expectedMessage = EditBookingCommand.MESSAGE_OVERLAP_BOOKING;
        assertCommandFailure(editBookingCommand, model, expectedMessage);
    }

    @Test
    public void execute_validEditBookingCommandSuccess() {
        Index validResidenceIndex = INDEX_SECOND_RESIDENCE;
        Index validBookingIndex = INDEX_FIRST_BOOKING;
        Residence targetResidence = model.getFilteredResidenceList().get(validResidenceIndex.getZeroBased());
        Residence residenceToEdit = model.getFilteredResidenceList().get(validResidenceIndex.getZeroBased());
        BookingList bookingListToEdit = residenceToEdit.getBookingList();
        Booking bookingToEdit = bookingListToEdit.getBooking(validBookingIndex.getZeroBased());
        Booking editedBooking = new BookingBuilder(bookingToEdit).withName(VALID_NAME_BOOKING2).build();
        EditBookingCommand editBookingCommand = new EditBookingCommand(validResidenceIndex, validBookingIndex,
                new EditBookingDescriptorBuilder().withName(VALID_NAME_BOOKING2).build());
        bookingListToEdit.setBooking(bookingToEdit, editedBooking);
        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);
        Model expectedModel = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());
        expectedModel.setResidence(targetResidence, residenceToEdit);
        expectedModel.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditBookingCommand standardCommand = new EditBookingCommand(
                INDEX_SECOND_RESIDENCE, INDEX_FIRST_BOOKING, new EditBookingDescriptor(DESC_BOOKING1));

        // same values -> returns true
        EditBookingDescriptor copyDescriptor = new EditBookingDescriptor(DESC_BOOKING1);
        EditBookingCommand commandWithSameValues = new EditBookingCommand(
                INDEX_SECOND_RESIDENCE, INDEX_FIRST_BOOKING, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(
                INDEX_FIRST_RESIDENCE, INDEX_FIRST_BOOKING, DESC_BOOKING1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBookingCommand(
                INDEX_SECOND_RESIDENCE, INDEX_FIRST_BOOKING, DESC_BOOKING2)));
    }


}
