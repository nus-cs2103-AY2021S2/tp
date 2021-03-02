package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.person.Person;


/**
 * Represents a task in SOChedule.
 */
public class Task {
    // Fields
    private final Name name;
    private final Date deadline;
    private final Priority priority;
    private final Category category;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Name field must be present and not null.
     */
    public Task(Name name, Date deadline, Priority priority, Category category, Set<Tag> tags) {
        requireAllNonNull(name);
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.category = category;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return this.name;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Category getCategory() {
        return this.category;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
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
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getCategory().equals(getCategory())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Priority: ")
                .append(getPriority())
                .append("; Category: ")
                .append(getCategory());

        Set<seedu.address.model.common.Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}


