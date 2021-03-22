package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.DESC_COURT;
import static seedu.booking.logic.commands.CommandTestUtil.DESC_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_CAPACITY_HALL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_VENUE_NAME_HALL;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.EditVenueCommand.EditVenueDescriptor;
import seedu.booking.testutil.EditVenueDescriptorBuilder;

public class EditVenueDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditVenueDescriptor descriptor = new EditVenueDescriptorBuilder(DESC_HALL).build();
        assertTrue(DESC_HALL.equals(descriptor));

        // same object -> returns true
        assertTrue(DESC_HALL.equals(DESC_HALL));

        // null -> returns false
        assertFalse(DESC_HALL.equals(null));

        // different types -> returns false
        assertFalse(DESC_HALL.equals(5));

        // different values -> returns false
        assertFalse(DESC_HALL.equals(DESC_COURT));

        // different name -> returns false
        EditVenueCommand.EditVenueDescriptor editedCourt =
                new EditVenueDescriptorBuilder(DESC_COURT).withVenueName(VALID_VENUE_NAME_HALL).build();
        assertFalse(DESC_COURT.equals(editedCourt));

        // different capacity -> returns false
        editedCourt = new EditVenueDescriptorBuilder(DESC_COURT).withCapacity(VALID_VENUE_CAPACITY_HALL).build();
        assertFalse(DESC_COURT.equals(editedCourt));

    }
}
