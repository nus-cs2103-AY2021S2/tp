package seedu.address.testutil;

import seedu.address.logic.commands.edit.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;

/**
 * A utility class to help with building EditPropertyDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setName(appointment.getName());
        descriptor.setRemarks(appointment.getRemarks());
        descriptor.setDate(appointment.getDate());
        descriptor.setTime(appointment.getTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Remarks} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withRemark(String remarks) {
        descriptor.setRemarks(new Remark(remarks));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDate(String date) {
        try {
            descriptor.setDate(ParserUtil.parseAppointmentDate(date));
            return this;
        } catch (ParseException ex) {
            return this;
        }
    }

    /**
     * Sets the {@code Time} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withTime(String time) {
        try {
            descriptor.setTime(ParserUtil.parseAppointmentTime(time));
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
