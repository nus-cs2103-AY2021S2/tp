package seedu.student.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.VALID_DATE_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.VALID_END_TIME_BOB_APPOINTMENT;
import static seedu.student.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.student.logic.commands.CommandTestUtil.VALID_START_TIME_BOB_APPOINTMENT;
import static seedu.student.testutil.TypicalAppointments.ALICE_APPOINTMENT;
import static seedu.student.testutil.TypicalAppointments.BOB_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.student.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(ALICE_APPOINTMENT.isSameAppointment(ALICE_APPOINTMENT));

        // null -> returns false
        assertFalse(ALICE_APPOINTMENT.isSameAppointment(null));

        // same matriculation number, all other attributes different -> returns true
        Appointment editedAliceAppointment = new AppointmentBuilder(ALICE_APPOINTMENT)
                .withDate(VALID_DATE_BOB_APPOINTMENT).withStartTime(VALID_START_TIME_BOB_APPOINTMENT)
                .withEndTime(VALID_END_TIME_BOB_APPOINTMENT).build();
        assertTrue(ALICE_APPOINTMENT.isSameAppointment(editedAliceAppointment));

        // different matriculation number, all other attributes same -> returns false
        editedAliceAppointment = new AppointmentBuilder(ALICE_APPOINTMENT).withMatric(VALID_MATRIC_BOB).build();
        assertFalse(ALICE_APPOINTMENT.isSameAppointment(editedAliceAppointment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment aliceAppointmentCopy = new AppointmentBuilder(ALICE_APPOINTMENT).build();
        assertTrue(ALICE_APPOINTMENT.equals(aliceAppointmentCopy));

        // same object -> returns true
        assertTrue(ALICE_APPOINTMENT.equals(ALICE_APPOINTMENT));

        // null -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(5));

        // different appointments -> returns false
        assertFalse(ALICE_APPOINTMENT.equals(BOB_APPOINTMENT));

        // different date -> returns false
        Appointment editedAliceAppointment = new AppointmentBuilder(ALICE_APPOINTMENT)
                .withDate(VALID_DATE_BOB_APPOINTMENT).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAliceAppointment));

        // different start time -> returns false
        editedAliceAppointment = new AppointmentBuilder(ALICE_APPOINTMENT)
                .withStartTime(VALID_START_TIME_BOB_APPOINTMENT).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAliceAppointment));

        // different end time -> returns false
        editedAliceAppointment = new AppointmentBuilder(ALICE_APPOINTMENT)
                .withEndTime(VALID_END_TIME_BOB_APPOINTMENT).build();
        assertFalse(ALICE_APPOINTMENT.equals(editedAliceAppointment));
    }
}
