package seedu.student.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.student.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.student.logic.commands.EditAppointmentCommand;
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

    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setMatriculationNumber(appointment.getMatriculationNumber());
        descriptor.setDate(appointment.getDate());
        descriptor.setStartTime(appointment.getStartTime());
    }

    public EditAppointmentDescriptorBuilder withMatric(String matric) {
        descriptor.setMatriculationNumber(new MatriculationNumber(matric));
        return this;
    }

    public EditAppointmentDescriptorBuilder withDate(LocalDate date) {
        descriptor.setDate(date);
        return this;
    }

    public EditAppointmentDescriptorBuilder withStartTime(LocalTime time) {
        descriptor.setStartTime(time);
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }

}
