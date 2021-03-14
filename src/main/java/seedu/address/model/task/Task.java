package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the planner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Title title;
    private final Deadline deadline;
    private final Email email;
    private final StartTime starttime;

    // Data fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, Deadline deadline, StartTime starttime, Email email,
                Description description, Set<Tag> tags) {
        requireAllNonNull(title, deadline, starttime, email, description, tags);
        this.title = title;
        this.deadline = deadline;
        this.starttime = starttime;
        this.email = email;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public StartTime getStartTime() {
        return starttime;
    }

    public Email getEmail() {
        return email;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both tasks have the same title.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle());
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
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getStartTime().equals(getStartTime())
                && otherTask.getEmail().equals(getEmail())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, deadline, starttime, email, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; StartTime: ")
                .append(getStartTime())
                .append("; Email: ")
                .append(getEmail())
                .append("; Description: ")
                .append(getDescription());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
