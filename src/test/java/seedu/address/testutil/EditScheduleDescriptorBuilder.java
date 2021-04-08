package seedu.address.testutil;

import seedu.address.logic.commands.schedulecommands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.common.Description;
import seedu.address.model.common.Title;
import seedu.address.model.schedule.Schedule;

/**
 * A utility class to help with building EditScheduleDescriptor objects.
 */
public class EditScheduleDescriptorBuilder {

    private EditScheduleDescriptor descriptor;

    public EditScheduleDescriptorBuilder() {
        descriptor = new EditScheduleDescriptor();
    }

    public EditScheduleDescriptorBuilder(EditScheduleDescriptor descriptor) {
        this.descriptor = new EditScheduleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditScheduleDescriptorBuilder} with fields containing {@code schedule}'s details
     */
    public EditScheduleDescriptorBuilder(Schedule schedule) {
        descriptor = new EditScheduleDescriptor();
        descriptor.setTitle(schedule.getTitle());
        descriptor.setDescription(schedule.getDescription());
        descriptor.setTimeFrom(schedule.getTimeFrom());
        descriptor.setTimeTo(schedule.getTimeTo());
    }

    /**
     * Sets the {@code Title} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code timeFrom} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withTimeFrom(String timeFrom) {
        descriptor.setTimeFrom(new AppointmentDateTime(timeFrom));
        return this;
    }

    /**
     * Sets the {@code timeTo} of the {@code EditScheduleDescriptorBuilder} that we are building.
     */
    public EditScheduleDescriptorBuilder withTimeTo(String timeTo) {
        descriptor.setTimeTo(new AppointmentDateTime(timeTo));
        return this;
    }

    public EditScheduleDescriptor build() {
        return descriptor;
    }
}
