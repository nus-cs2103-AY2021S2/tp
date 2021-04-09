package seedu.address.testutil;

import seedu.address.logic.commands.remindercommands.EditReminderCommand.EditReminderDescriptor;
import seedu.address.model.common.Description;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDate;

/**
 * A utility class to help with building EditReminderDescriptor objects.
 */
public class EditReminderDescriptorBuilder {

    private EditReminderDescriptor descriptor;

    public EditReminderDescriptorBuilder() {
        descriptor = new EditReminderDescriptor();
    }

    public EditReminderDescriptorBuilder(EditReminderDescriptor descriptor) {
        this.descriptor = new EditReminderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditReminderDescriptorBuilder} with fields containing {@code reminder}'s details
     */
    public EditReminderDescriptorBuilder(Reminder reminder) {
        descriptor = new EditReminderDescriptor();
        descriptor.setDescription(reminder.getDescription());
        descriptor.setReminderDate(reminder.getReminderDate());
    }

    /**
     * Sets the {@code Description} of the {@code EditReminderDescriptorBuilder} that we are building.
     */
    public EditReminderDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code timeFrom} of the {@code EditReminderDescriptorBuilder} that we are building.
     */
    public EditReminderDescriptorBuilder withReminderDate(String reminderDate) {
        descriptor.setReminderDate(new ReminderDate(reminderDate));
        return this;
    }

    public EditReminderDescriptor build() {
        return descriptor;
    }
}
