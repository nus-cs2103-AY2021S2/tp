package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;


public class AppointmentListTest {

    private AppointmentList appointmentList = new AppointmentList();
    private List<Appointment> typicalAppointments = getTypicalAppointments();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDateTime(null));
    }

    @Test
    public void execute_addDuplicateAppointment_throwsDuplicateAppointmentEXception() {
        appointmentList.add(typicalAppointments.get(0));
        assertThrows(DuplicateAppointmentException.class, () -> appointmentList.add(
                typicalAppointments.get(0)));

    }

    @Test
    public void execute_replaceMissingAppointment_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> appointmentList
                .setAppointment(typicalAppointments.get(1), typicalAppointments.get(0)));
    }

    @Test
    public void equals() {
        assertFalse(appointmentList.contains(typicalAppointments.get(0)));

        Appointment appointment1 = typicalAppointments.get(0);
        AppointmentList appointmentList1 = new AppointmentList();
        AppointmentList appointmentList2 = new AppointmentList();
        appointmentList1.add(appointment1);
        appointmentList2.add(appointment1);
        assertEquals(appointmentList1, appointmentList2);

        appointmentList1.remove(0);
        assertFalse(appointmentList1.contains(appointment1));
    }


}
