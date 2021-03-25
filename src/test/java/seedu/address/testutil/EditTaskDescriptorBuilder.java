package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskName;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.Tag;

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
        descriptor.setTaskName(task.getTaskName());
        descriptor.setModuleCode(task.getModuleCode());
        descriptor.setDeadlineDate(task.getDeadlineDate());
        descriptor.setDeadlineTime(task.getDeadlineTime());
        descriptor.setWeightage(task.getWeightage());
        descriptor.setRemark(task.getRemark());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code TaskName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String name) {
        descriptor.setTaskName(new TaskName(name));
        return this;
    }

    /**
     * Sets the {@code TaskName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withCode(String code) {
        descriptor.setModuleCode(new ModuleCode(code));
        return this;
    }

    /**
     * Sets the {@code DeadlineDate} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadlineDate(String deadlineDate) {
        descriptor.setDeadlineDate(new DeadlineDate(deadlineDate));
        return this;
    }

    /**
     * Sets the {@code DeadlineTime} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadlineTime(String deadlineTime) {
        descriptor.setDeadlineTime(new DeadlineTime(deadlineTime));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withWeightage(Integer weightage) {
        descriptor.setWeightage(new Weightage(weightage));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
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
