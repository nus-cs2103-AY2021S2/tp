package seedu.taskify.model.task;

import static seedu.taskify.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.taskify.model.tag.Tag;

/**
 * Represents a Task in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;
    private final Description description;
    private Status status;

    // Data fields
    private final Date date;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Description description, Status status, Date date, Set<Tag> tags) {
        requireAllNonNull(name, description, status, date, tags);
        this.name = name;
        this.description = description;
        this.status = status;
        this.date = date;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    /**
     * Check to see if the task has expired
     */

    public boolean isTaskExpired() {
        LocalDateTime timeNow = LocalDateTime.now();
        if (this.date.getLocateDateTime().isBefore(timeNow)) {
            this.status = new Status(StatusType.EXPIRED);
            return true;
        }
        return false;
    }

    public boolean isTaskCompleted() {
        return this.getStatus().toString().equals("Completed");
    }

    public boolean isTaskUncompleted() {
        return this.getStatus().toString().equals("Uncompleted");
    }

    /**
     * Checks whether a task has a deadline date on the current day
     */
    public boolean isTodaysTask() {
        LocalDate todaysDate = LocalDateTime.now().toLocalDate();
        return this.date.getLocalDateTime().toLocalDate().equals(todaysDate);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
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
                       && otherTask.getDescription().equals(getDescription())
                       && otherTask.getStatus().equals(getStatus())
                       && otherTask.getDate().equals(getDate())
                       && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, status, date, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Status: ")
                .append(getStatus())
                .append("; Date: ")
                .append(getDate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
