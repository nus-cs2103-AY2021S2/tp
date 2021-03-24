package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Duration;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setTitle(task.getTitle());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setDuration(task.getDuration());
        descriptor.setRecurringSchedule(task.getRecurringSchedule());
        descriptor.setDescription(task.getDescription());
        descriptor.setStatus(task.getStatus());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }
    /**
     * Sets the {@code Duration} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Sets the {@code RecurringSchedule} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withRecurringSchedule(String recurringSchedule) {
        descriptor.setRecurringSchedule(new RecurringSchedule(recurringSchedule));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
