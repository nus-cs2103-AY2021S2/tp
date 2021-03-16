package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DES_NAME;
import static seedu.address.logic.commands.CommandTestUtil.ASC_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_ORDER_ASC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORTING_KEY_APPOINTMENT_DATETIME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortAppointmentCommand.SortAppointmentDescriptor;
import seedu.address.testutil.SortAppointmentDescriptorBuilder;

public class SortAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        SortAppointmentDescriptor descriptorWithSameValues = new SortAppointmentDescriptor(DES_NAME);
        assertTrue(DES_NAME.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DES_NAME.equals(DES_NAME));

        // null -> returns false
        assertFalse(DES_NAME.equals(null));

        // different types -> returns false
        assertFalse(DES_NAME.equals(5));

        // different values -> returns false
        assertFalse(DES_NAME.equals(ASC_DATETIME));

        // different sorting orders -> returns false
        SortAppointmentDescriptor ascName = new SortAppointmentDescriptorBuilder(DES_NAME)
                .withSortingOrder(VALID_SORTING_ORDER_ASC).build();
        assertFalse(DES_NAME.equals(ascName));

        // different sorting keys -> returns false
        SortAppointmentDescriptor desDatetime = new SortAppointmentDescriptorBuilder(DES_NAME)
                .withAppointmentSortingKey(VALID_SORTING_KEY_APPOINTMENT_DATETIME).build();
        assertFalse(DES_NAME.equals(desDatetime));
    }
}
