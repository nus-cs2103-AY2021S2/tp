package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Task takes in priorityTag as an additional attribute
     */

    public Task(TaskName taskName, ModuleCode moduleCode, DeadlineDate deadlineDate,
                DeadlineTime deadlineTime, Status status, Weightage weightage,
                Remark remark, Set<Tag> tags, PriorityTag priorityTag) {
        requireAllNonNull(taskName, moduleCode, status, tags);
        this.taskName = taskName;
        this.moduleCode = moduleCode;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        this.status = status;
        this.weightage = weightage;
        this.remark = remark;
        tags = removeOldPriorityTags(tags);
        this.tags.addAll(tags);
        this.priorityTag = priorityTag;
        this.tags.add(new Tag(this.priorityTag.getTagName()));

    }

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

        if (findPriorityTag(this.tags)) {
            this.priorityTag = obtainPriorityTag(this.tags);
        } else {
            this.priorityTag = new PriorityTag("LOW");
            this.tags.add(new Tag("LOW"));
        }


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
                this.deadlineTime, this.status.toggle(), this.weightage, this.remark, this.tags);
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
            .append(getRemark())
                .append("; PriorityTag: ")
                .append(getPriorityTag().getTagName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     *
     * Method that finds if there is a priority tag associated
     * with the tags
     *
     * @param tags data containing all the tags in String
     * @return boolean whether the pt is found
     */

    private boolean findPriorityTag(Set<Tag> tags) {

        Iterator<Tag> it = tags.iterator();

        while (it.hasNext()) {
            Tag hold = it.next();
            if (hold.tagName.equals("LOW")
                    || hold.tagName.equals("MEDIUM")
                    || hold.tagName.equals("HIGH")) {
                return true;
            }
        }

        return false;

    }

    /**
     *
     * Method that returns a priority tag associated
     * with the task
     *
     * @param tags data containing all the tags in String
     * @return the priority tag to be stored
     */

    private PriorityTag obtainPriorityTag(Set<Tag> tags) {

        Iterator<Tag> it = tags.iterator();

        while (it.hasNext()) {
            Tag hold = it.next();
            if (hold.tagName.equals("LOW")
                    || hold.tagName.equals("MEDIUM")
                    || hold.tagName.equals("HIGH")) {
                return new PriorityTag(hold.tagName);
            }
        }

        return new PriorityTag("LOW");

    }

    /**
     * method to remove outdated instances of priority tags in set
     * @param tags data struct that stores the tags
     * @return a tag set free of old ptag
     */

    private Set<Tag> removeOldPriorityTags(Set<Tag> tags) {
        Iterator<Tag> it = tags.iterator();
        Set<Tag> hold = new HashSet<>();
        while (it.hasNext()) {
            Tag check = it.next();
            if (check.tagName.equals("LOW")
                    || check.tagName.equals("MEDIUM")
                    || check.tagName.equals("HIGH")) {
            } else {
                hold.add(check);
            }
        }
        return hold;
    }

}
