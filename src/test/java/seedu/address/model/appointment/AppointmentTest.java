package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAppointments.MATHS_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.SCIENCE_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void isSameAppointment() {
        assertTrue(MATHS_APPOINTMENT.isSameAppointment(MATHS_APPOINTMENT));

        assertFalse(MATHS_APPOINTMENT.isSameAppointment(null));

        assertFalse(MATHS_APPOINTMENT.isSameAppointment(SCIENCE_APPOINTMENT));

    }


    @Test
    public void equals() {
        Appointment mathApppointment =
                new AppointmentBuilder(MATHS_APPOINTMENT).build();

        assertEquals(mathApppointment, MATHS_APPOINTMENT);

        assertNotEquals(MATHS_APPOINTMENT, null);

        assertNotEquals(MATHS_APPOINTMENT, SCIENCE_APPOINTMENT);

        // Changed Name
        Appointment editedNameMath =
                new AppointmentBuilder(MATHS_APPOINTMENT).withName("Wrong Name").build();
        assertNotEquals(editedNameMath, MATHS_APPOINTMENT);

        // Changed subjectName
        Appointment editedSubjectMath =
                new AppointmentBuilder(MATHS_APPOINTMENT).withSubject("Wrong Name").build();
        assertNotEquals(editedNameMath, MATHS_APPOINTMENT);

    }


}
