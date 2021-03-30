package seedu.student.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.student.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;

public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Constructor for the EditAppointmentDescriptorBuilder object.
     * @param appointment an Appointment object
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setMatriculationNumber(appointment.getMatriculationNumber());
        descriptor.setDate(appointment.getDate());
        descriptor.setStartTime(appointment.getStartTime());
    }

    /**
     * Specifies the matriculation number field in the EditAppointmentDescriptor.
     * @param matric matriculation number of a student used to identify an appointment.
     * @return an EditAppointmentDescriptorBuilder
     */
    public EditAppointmentDescriptorBuilder withMatric(String matric) {
        descriptor.setMatriculationNumber(new MatriculationNumber(matric));
        return this;
    }

    /**
     * Specifies the date field in the EditAppointmentDescriptor.
     * @param date date of an appointment.
     * @return an EditAppointmentDescriptorBuilder
     */
    public EditAppointmentDescriptorBuilder withDate(LocalDate date) {
        descriptor.setDate(date);
        return this;
    }

    /**
     * Specifies the start time field in the EditAppointmentDescriptor.
     * @param time start time of an appointment.
     * @return an EditAppointmentDescriptorBuilder
     */
    public EditAppointmentDescriptorBuilder withStartTime(LocalTime time) {
        descriptor.setStartTime(time);
        return this;
    }

    /**
     * Builds an EditAppointmentDescriptor.
     * @return an EditAppointmentDescriptor object.
     */
    public EditAppointmentDescriptor build() {
        return descriptor;
    }

}
