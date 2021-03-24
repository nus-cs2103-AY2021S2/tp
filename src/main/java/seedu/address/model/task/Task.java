package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;


/**
 * Represents a task in SOChedule.
 */
public class Task {
    // Fields
    private final Name name;
    private final Date deadline;
    private final Priority priority;
    private final CompletionStatus completionStatus = new CompletionStatus();
    private final Set<Category> categories = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Name field must be present and not null.
     */
    public Task(Name name, Date deadline, Priority priority, Set<Category> categories, Set<Tag> tags) {
        requireAllNonNull(name);
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.categories.addAll(categories);
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

    public CompletionStatus getCompletionStatus() {
        return this.completionStatus;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isComplete() {
        return completionStatus.isComplete();
    }

    public void markTaskAsDone() {
        completionStatus.markAsDone();
    }

    /**
     * Returns true if both tasks have the same name, deadline, priority, tags and categories.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getCategories().equals(getCategories())
                && otherTask.getTags().equals(getTags());
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
                && otherTask.getCategories().equals(getCategories())
                && otherTask.getTags().equals(getTags())
                && otherTask.getCompletionStatus().equals(getCompletionStatus());
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
                .append(getCategories())
                .append("; Completion Status: ")
                .append(completionStatus.toString());

        Set<seedu.address.model.common.Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }


}


