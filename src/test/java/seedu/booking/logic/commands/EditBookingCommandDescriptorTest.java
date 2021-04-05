package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_COMMAND_DESCRIPTOR_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_COMMAND_DESCRIPTOR_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_DESCRIPTION_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_END_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_BOOKING_START_FIELD;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_COURT;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.EditBookingCommandDescriptorBuilder;

public class EditBookingCommandDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditBookingCommand.EditBookingDescriptor descriptorWithSameValues =
                new EditBookingCommand.EditBookingDescriptor(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL);
        assertTrue(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL));

        // null -> returns false
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(null));

        // different types -> returns false
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(5));

        // different values -> returns false
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(VALID_BOOKING_COMMAND_DESCRIPTOR_FIELD));

        // different email -> returns false
        EditBookingCommand.EditBookingDescriptor editedHall =
                new EditBookingCommandDescriptorBuilder(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL)
                        .withBookerEmail(VALID_EMAIL_BOB).build();
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(editedHall));

        // different venue name -> returns false
        editedHall = new EditBookingCommandDescriptorBuilder(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL)
                .withVenueName(VALID_VENUE_NAME_COURT).build();
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(editedHall));

        // different description -> returns false
        editedHall = new EditBookingCommandDescriptorBuilder(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL)
                .withDescription(VALID_BOOKING_DESCRIPTION_FIELD).build();
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(editedHall));

        // different start time -> returns false
        editedHall = new EditBookingCommandDescriptorBuilder(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL)
                .withBookingStart(VALID_BOOKING_START_FIELD).build();
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(editedHall));

        // different end time -> returns false
        editedHall = new EditBookingCommandDescriptorBuilder(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL)
                .withBookingEnd(VALID_BOOKING_END_FIELD).build();
        assertFalse(VALID_BOOKING_COMMAND_DESCRIPTOR_HALL.equals(editedHall));

    }
}
