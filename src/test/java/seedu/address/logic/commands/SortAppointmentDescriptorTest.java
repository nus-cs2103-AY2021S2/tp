package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ASC_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_APPOINTMENT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_ASC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.testutil.SortAppointmentDescriptorBuilder;

public class SortAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        SortAppointmentDescriptor descriptorWithSameValues = new SortAppointmentDescriptor(DESC_APPOINTMENT_NAME);
        assertTrue(DESC_APPOINTMENT_NAME.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPOINTMENT_NAME.equals(DESC_APPOINTMENT_NAME));

        // null -> returns false
        assertFalse(DESC_APPOINTMENT_NAME.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPOINTMENT_NAME.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPOINTMENT_NAME.equals(ASC_DATETIME));

        // different sorting orders -> returns false
        SortAppointmentDescriptor ascName = new SortAppointmentDescriptorBuilder(DESC_APPOINTMENT_NAME)
                .withSortingOrder(VALID_SORTING_ORDER_ASC).build();
        assertFalse(DESC_APPOINTMENT_NAME.equals(ascName));

        // different sorting keys -> returns false
        SortAppointmentDescriptor desDatetime = new SortAppointmentDescriptorBuilder(DESC_APPOINTMENT_NAME)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME).build();
        assertFalse(DESC_APPOINTMENT_NAME.equals(desDatetime));
    }
}
