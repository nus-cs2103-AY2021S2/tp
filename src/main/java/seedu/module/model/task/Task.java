package seedu.module.model.task;

import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.module.model.tag.Tag;

/**
 * Represents a Task in the module book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;
    private final Deadline deadline;
    private final Module module;
    private boolean isRecurringTask;

    // Data fields
    private final Description description;
    private final Workload workload;
    private final DoneStatus doneStatus;
    private final Set<Tag> tags = new HashSet<>();
    private Recurrence recurrence;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Set<Tag> tags) {
        requireAllNonNull(name, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.isRecurringTask = false;
    }

    /**
     * Overloaded constructor for Task when an optional Recurrence is passed in.
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Recurrence recurrence, Set<Tag> tags) {
        requireAllNonNull(name, deadline, module, description, workload, doneStatus, recurrence, tags);
        this.name = name;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.recurrence = recurrence;
        this.isRecurringTask = true;
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Module getModule() {
        return module;
    }

    public Description getDescription() {
        return description;
    }

    public Workload getWorkload() {
        return workload;
    }

    public DoneStatus getDoneStatus() {
        return doneStatus;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public boolean getIsRecurringTaskStatus() {
        return isRecurringTask;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks have the same name and same module code.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getModule().equals(getModule());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
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
        return otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getModule().equals(getModule())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getWorkload().equals(getWorkload())
                && otherTask.getDoneStatus().equals(getDoneStatus())
                && otherTask.getTags().equals(getTags())
                && otherTask.getRecurrence().equals(getRecurrence());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, module, description, workload, doneStatus, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Module: ")
                .append(getModule())
                .append("; Description: ")
                .append(getDescription())
                .append("; Workload: ")
                .append(getWorkload())
                .append("; Completion Status: ")
                .append(getDoneStatus());

        if (isRecurringTask) {
            builder.append("; Recurring: ").append(getRecurrence());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    /**
     * Comparator of tasks using deadline as reference
     */
    public static class DeadlineComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2) {
            return t1.getDeadline().compareTo(t2.getDeadline());
        }
    }

}
