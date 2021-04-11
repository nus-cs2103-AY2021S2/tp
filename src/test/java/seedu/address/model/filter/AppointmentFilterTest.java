package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.TypicalAppointments;
import seedu.address.testutil.TypicalFilters;

/**
 * Integration tests for AppointmentFilter.
 */
public class AppointmentFilterTest {
    @Test
    public void test() {
        Appointment maths = TypicalAppointments.MATHS_APPOINTMENT;
        Appointment science = TypicalAppointments.SCIENCE_APPOINTMENT;

        AppointmentFilter appointmentFilter = TypicalFilters.getMathFilter();

        assertTrue(appointmentFilter.test(maths));
        assertFalse(appointmentFilter.test(science));
    }
}
