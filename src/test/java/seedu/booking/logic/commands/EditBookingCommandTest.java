package seedu.booking.logic.commands;

import static seedu.booking.testutil.TypicalBookings.getTypicalBookingSystem;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;

class EditBookingCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
    //        Booking editedBooking = new BookingBuilder().build();
    //        EditBookingCommand.EditBookingDescriptor descriptor =
    //              new EditBookingCommandDescriptorBuilder(editedBooking).build();
    //        EditBookingCommand editCommand = new EditBookingCommand(INDEX_FIRST, descriptor);
    //
    //        String expectedMessage = String.format(EditBookingCommand.MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking);
    //
    //        Model expectedModel = new ModelManager(new BookingSystem(model.getBookingSystem()), new UserPrefs());
    //        expectedModel.setBooking(model.getFilteredBookingList().get(0), editedBooking);
    //
    //        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute() {
    }

    @Test
    void testEquals() {
    }
}
