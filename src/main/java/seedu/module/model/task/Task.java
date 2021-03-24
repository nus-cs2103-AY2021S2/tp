package seedu.module.model.task;

import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;

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

    public static String INVALID_START_TIME = "Start time have to be earlier than end time";

    // Identity fields
    private final Name name;
    private Time startTime;
    private final Time deadline;
    private final Module module;
    private final boolean isDeadline;

    // Data fields
    private final Description description;
    private final Workload workload;
    private final DoneStatus doneStatus;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Set<Tag> tags) {
        requireAllNonNull(name, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.isDeadline = true;
    }

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Time startTime, Time deadline, Module module, Description description,
                Workload workload, DoneStatus doneStatus, Set<Tag> tags) {
        requireAllNonNull(name, startTime, deadline, module, description, workload, doneStatus, tags);
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
        this.module = module;
        this.description = description;
        this.workload = workload;
        this.doneStatus = doneStatus;
        this.tags.addAll(tags);
        this.isDeadline = false;
    }

    public Name getName() {
        return name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getDeadline() {
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

    public boolean isDeadline() {
        return isDeadline;
    }

    public boolean isTimeInvalid() {
        return this.startTime.compareTo(this.deadline) >= 0;
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
                && otherTask.getStartTime().equals(getStartTime())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getModule().equals(getModule())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getWorkload().equals(getWorkload())
                && otherTask.getDoneStatus().equals(getDoneStatus())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, startTime, deadline, module, description, workload, doneStatus, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName());
        if (isDeadline) {
            builder.append("; Deadline: ")
                    .append(getDeadline());
        } else {
            builder.append("; Start: ")
                    .append(getStartTime())
                    .append("; End: ")
                    .append(getDeadline());
        }
        builder.append("; Module: ")
                .append(getModule())
                .append("; Description: ")
                .append(getDescription())
                .append("; Workload: ")
                .append(getWorkload())
                .append("; Completion Status: ")
                .append(getDoneStatus());

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
            if (t1.isDeadline && t2.isDeadline) {
                return t1.getDeadline().compareTo(t2.getDeadline());
            } else if (t1.isDeadline) {
                return t1.getDeadline().compareTo(t2.getStartTime());
            } else if (t2.isDeadline) {
                return t1.getStartTime().compareTo(t2.getDeadline());
            } else {
                return t1.getStartTime().compareTo(t2.getStartTime());
            }
        }
    }

}
