package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_BOOKINGS;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_COMMAND_DESCRIPTOR_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_COMMAND_DESCRIPTOR_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.testutil.TypicalBookings.BOOKING_FIELD;
import static seedu.booking.testutil.TypicalBookings.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.booking.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.booking.testutil.TypicalPersons.BOB;
import static seedu.booking.testutil.TypicalVenues.VENUE1;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.core.index.Index;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.Booking;
import seedu.booking.testutil.BookingBuilder;
import seedu.booking.testutil.EditBookingCommandDescriptorBuilder;

class EditBookingCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Booking editedBooking = new BookingBuilder().build();
        model.addVenue(VENUE1);
        model.addPerson(BOB);
        EditBookingCommand.EditBookingDescriptor descriptor =
              new EditBookingCommandDescriptorBuilder(editedBooking).build();
        EditBookingCommand editCommand = new EditBookingCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editCommand, model, expectedMessage, COMMAND_SHOW_BOOKINGS, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model.addPerson(BOB);
        model.addVenue(VENUE1);
        Index indexLastBooking = Index.fromOneBased(model.getFilteredBookingList().size());
        Booking lastBooking = model.getFilteredBookingList().get(indexLastBooking.getZeroBased());

        BookingBuilder bookingInList = new BookingBuilder(lastBooking);
        Booking editedBooking = bookingInList
                .withVenue(VENUE1.getVenueName().venueName)
                .withBooker(BOB.getEmail().value)
                .withDescription("VIP")
                .withBookingStart("2021-02-02 07:00")
                .withBookingEnd("2021-02-02 08:00")
                .build();

        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withVenueName(VENUE1.getVenueName().venueName)
                .withBookerEmail(BOB.getEmail().value)
                .withDescription("VIP")
                .withBookingStart("2021-02-02 07:00")
                .withBookingEnd("2021-02-02 08:00")
                .build();
        EditBookingCommand editCommand = new EditBookingCommand(indexLastBooking, descriptor);

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setBooking(lastBooking, editedBooking);

        assertCommandSuccess(editCommand, model, expectedMessage, COMMAND_SHOW_BOOKINGS, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        model.addPerson(BOB);
        model.addVenue(VENUE1);
        model.addBooking(BOOKING_FIELD);
        EditBookingCommand editBookingCommand =
                new EditBookingCommand(INDEX_FIRST, new EditBookingCommand.EditBookingDescriptor());
        Booking editedBooking = model.getFilteredBookingList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        assertCommandSuccess(editBookingCommand, model, expectedMessage, COMMAND_SHOW_BOOKINGS, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model.addVenue(VENUE1);
        Booking bookingInFilteredList = model.getFilteredBookingList().get(INDEX_FIRST.getZeroBased());
        Booking editedBooking = new BookingBuilder(bookingInFilteredList)
                .withVenue(VENUE1.getVenueName().venueName).build();
        EditBookingCommand editCommand = new EditBookingCommand(INDEX_FIRST,
                new EditBookingCommandDescriptorBuilder().withVenueName(VENUE1.getVenueName().venueName).build());

        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);

        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);

        assertCommandSuccess(editCommand, model, expectedMessage, COMMAND_SHOW_BOOKINGS, expectedModel);
    }

    @Test
    public void execute_duplicateBookingUnfilteredList_failure() {
        Booking firstBooking = model.getFilteredBookingList().get(INDEX_FIRST.getZeroBased());
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder(firstBooking)
                .build();
        EditBookingCommand editCommand = new EditBookingCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, EditBookingCommand.MESSAGE_DUPLICATE_BOOKING);
    }

    @Test
    public void execute_duplicateBookingFilteredList_failure() {
        Booking bookingInList = model.getBookingSystem().getBookingList().get(INDEX_SECOND.getZeroBased());
        EditBookingCommand editCommand = new EditBookingCommand(INDEX_FIRST,
                new EditBookingCommandDescriptorBuilder(bookingInList).build());

        assertCommandFailure(editCommand, model, EditBookingCommand.MESSAGE_DUPLICATE_BOOKING);
    }

    @Test
    public void execute_invalidBookingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        EditBookingCommand.EditBookingDescriptor descriptor = new EditBookingCommandDescriptorBuilder()
                .withBookerEmail(VALID_EMAIL_BOB).build();
        EditBookingCommand editCommand = new EditBookingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_BOOKING_INDEX_OUT_OF_RANGE);
    }

    @Test
    public void equals() {
        final EditBookingCommand standardCommand =
                new EditBookingCommand(INDEX_FIRST, VALID_BOOKING_COMMAND_DESCRIPTOR_HALL);

        // same values -> returns true
        EditBookingCommand.EditBookingDescriptor copyDescriptor =
                new EditBookingCommand.EditBookingDescriptor(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL);
        EditBookingCommand commandWithSameValues = new EditBookingCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditBookingCommand(INDEX_SECOND, VALID_BOOKING_COMMAND_DESCRIPTOR_HALL)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditBookingCommand(INDEX_FIRST, VALID_BOOKING_COMMAND_DESCRIPTOR_FIELD)));
    }



}
