package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final TaskName taskName;
    private final ModuleCode moduleCode;
    private final DeadlineDate deadlineDate;
    private final DeadlineTime deadlineTime;
    private final Status status;
    private final Weightage weightage;

    // Data fields
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, ModuleCode moduleCode, DeadlineDate deadlineDate,
                DeadlineTime deadlineTime, Status status, Weightage weightage,
                Remark remark, Set<Tag> tags) {
        requireAllNonNull(taskName, moduleCode, status, tags);
        this.taskName = taskName;
        this.moduleCode = moduleCode;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.status = status;
        this.weightage = weightage;
        this.remark = remark;
        this.tags.addAll(tags);
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public DeadlineDate getDeadlineDate() {
        return deadlineDate;
    }

    public DeadlineTime getDeadlineTime() {
        return deadlineTime;
    }

    public Status getStatus() {
        return status;
    }

    public Weightage getWeightage() {
        return weightage;
    }

    public Remark getRemark() {
        return remark;
    }

    public boolean hasFinished() {
        return status.hasFinished();
    }

    /**
     * Finish a task and return a new Task with status finished
     */
    public Task finishTask() {
        status.finish();
        return new Task(this.taskName, this.moduleCode, this.deadlineDate,
                this.deadlineTime, this.status, this.weightage, this.remark, this.tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same taskName.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
            && otherTask.getTaskName().equals(getTaskName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTaskName().equals(getTaskName())
            && otherTask.getModuleCode().equals(getModuleCode())
            && otherTask.getDeadlineDate().equals(getDeadlineDate())
            && otherTask.getDeadlineTime().equals(getDeadlineTime())
            && otherTask.getStatus().equals(getStatus())
            && otherTask.getWeightage().equals(getWeightage())
            && otherTask.getRemark().equals(getRemark())
            && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, moduleCode, weightage, remark, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskName())
            .append("; Module Code: ")
            .append(getModuleCode())
            .append("; Deadline Date: ")
            .append(getDeadlineDate())
            .append("; Deadline Time: ")
            .append(getDeadlineTime())
            .append("; Status: ")
            .append(getStatus())
            .append("; Weightage: ")
            .append(getWeightage())
            .append("; Remark: ")
            .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
