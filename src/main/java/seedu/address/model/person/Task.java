package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.PriorityTag;
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
    private final PriorityTag priorityTag;

    // Data fields
    private final Notes notes;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Task takes in priorityTag as an additional attribute
     */
    public Task(TaskName taskName, ModuleCode moduleCode, DeadlineDate deadlineDate,
                DeadlineTime deadlineTime, Status status, Weightage weightage,
                Notes notes, Set<Tag> tags, PriorityTag priorityTag) {
        requireAllNonNull(taskName, moduleCode, status, tags);
        this.taskName = taskName;
        this.moduleCode = moduleCode;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.status = status;
        this.weightage = weightage;
        this.notes = notes;
        this.tags.addAll(tags);
        this.priorityTag = priorityTag;

    }

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName taskName, ModuleCode moduleCode, DeadlineDate deadlineDate,
                DeadlineTime deadlineTime, Status status, Weightage weightage,
                Notes notes, Set<Tag> tags) {
        requireAllNonNull(taskName, moduleCode, status, tags);
        this.taskName = taskName;
        this.moduleCode = moduleCode;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.status = status;
        this.weightage = weightage;
        this.notes = notes;
        this.tags.addAll(tags);
        this.priorityTag = new PriorityTag("LOW");


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

    public Notes getNotes() {
        return notes;
    }

    public PriorityTag getPriorityTag() {
        return priorityTag;
    }

    public boolean hasFinished() {
        return status.hasFinished();
    }

    /**
     * Finish a task and return a new Task with status finished
     */
    public Task finishTask() {
        return new Task(this.taskName, this.moduleCode, this.deadlineDate,
                this.deadlineTime, this.status.toggle(), this.weightage, this.notes, this.tags, this.priorityTag);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks have the same taskName and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == null) {
            return false;
        }

        if (otherTask.equals(this)) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if both tasks have the same task name and data fields.
     * This defines a stronger notion of equality between two tasks.
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
            && otherTask.getNotes().equals(getNotes())
            && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(taskName, moduleCode, weightage, notes, tags);
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
            .append("; Notes: ")
            .append(getNotes())
            .append("; PriorityTag: ")
            .append(getPriorityTag().getTagName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
