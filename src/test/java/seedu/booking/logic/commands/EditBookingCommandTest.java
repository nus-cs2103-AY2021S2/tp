package seedu.booking.logic.commands;

import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_VENUE1;
import static seedu.booking.testutil.TypicalBookings.getTypicalBookingSystem;
import static seedu.booking.testutil.TypicalBookings.BOOKING_FIELD;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.booking.testutil.TypicalVenues.FIELD;
import static seedu.booking.testutil.TypicalVenues.VENUE1;
import static seedu.booking.testutil.TypicalPersons.BOB;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.index.Index;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;
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

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model.addPerson(BOB);
        model.addVenue(VENUE1);
        Index indexLastBooking = Index.fromOneBased(model.getFilteredBookingList().size());
        Booking lastBooking = model.getFilteredBookingList().get(indexLastBooking.getZeroBased());

        BookingBuilder bookingInList = new BookingBuilder(lastBooking);
        Booking editedBooking = bookingInList.withVenue(VENUE1.getVenueName().venueName)
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

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
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
        assertCommandSuccess(editBookingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);
//
//        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
//        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
