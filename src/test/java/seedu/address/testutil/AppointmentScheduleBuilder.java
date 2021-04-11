package seedu.address.testutil;

import seedu.address.model.AppointmentSchedule;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class to help with building AppointmentSchedule objects.
 * Example usage: <br>
 *     {@code AppointmentSchedule as = new AppointmentScheduleBuilder().withAppointment(appointment).build();}
 */
public class AppointmentScheduleBuilder {

    private AppointmentSchedule appointmentSchedule;

    public AppointmentScheduleBuilder() {
        appointmentSchedule = new AppointmentSchedule();
    }

    public AppointmentScheduleBuilder(AppointmentSchedule appointmentSchedule) {
        this.appointmentSchedule = appointmentSchedule;
    }

    /**
     * Adds a new {@code Appointment} to the {@code AppointmentSchedule} that we are building.
     */
    public AppointmentScheduleBuilder withAppointment(Appointment appointment) {
        appointmentSchedule.addAppointment(appointment);
        return this;
    }

    public AppointmentSchedule build() {
        return appointmentSchedule;
    }
}
