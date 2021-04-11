package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;

/**
 * Represents a Task in the planner.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Title title;
    private final Date date;
    private final Duration duration;

    // Data fields
    private final Description description;
    private final RecurringSchedule recurringSchedule;
    private final Status status;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Title title, Date date, Duration duration, RecurringSchedule recurringSchedule,
                Description description, Status status, Set<Tag> tags) {
        // All fields are not null even if its value is blank.
        requireAllNonNull(title, date, duration, recurringSchedule, description, status, tags);
        this.title = title;
        this.date = date;
        this.duration = duration;
        this.recurringSchedule = recurringSchedule;
        this.description = description;
        this.status = status;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Duration getDuration() {
        return duration;
    }

    public RecurringSchedule getRecurringSchedule() {
        return recurringSchedule;
    }

    public Description getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Replaces the task with a new Task with the set of tags provided.
     */
    public Task setTags(Set<Tag> tagSet) {
        return new Task(title, date, duration, recurringSchedule, description, status, tagSet);
    }

    /**
     * Gets a hashmap of each field name and its string value. Insertion order matters for the
     * TaskCardDetails class that invokes this method to dynamically use entries.
     *
     * @return Hashmap of field names and their string values.
     */
    public HashMap<String, String> getFields() {
        HashMap<String, String> optionalFieldMap = new HashMap<>();
        optionalFieldMap.put(Title.FIELD_NAME, title.toString());
        optionalFieldMap.put(Duration.FIELD_NAME, duration.toString());
        optionalFieldMap.put(Status.FIELD_NAME, status.toString());
        optionalFieldMap.put(Date.FIELD_NAME, date.toString());
        optionalFieldMap.put(Description.FIELD_NAME, description.toString());
        optionalFieldMap.put(RecurringSchedule.FIELD_NAME, recurringSchedule.toString());
        return optionalFieldMap;
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
                && otherTask.getDate().equals(getDate())
                && otherTask.getDuration().equals(getDuration())
                && otherTask.getRecurringSchedule().equals(getRecurringSchedule())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getStatus().equals(getStatus())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, date, duration, recurringSchedule, description, status, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle()).append("; \n");
        if (!date.isEmptyValue()) {
            builder.append(Date.FIELD_NAME).append(": ").append(getDate()).append("; \n");
        }
        if (!duration.isEmptyValue()) {
            builder.append(Duration.FIELD_NAME).append(": ").append(getDuration()).append("; \n");
        }
        if (!recurringSchedule.isEmptyValue()) {
            builder.append(RecurringSchedule.FIELD_NAME).append(": ").append(getRecurringSchedule()).append("; \n");
        }
        if (!description.isEmptyValue()) {
            builder.append(Description.FIELD_NAME).append(": ").append(getDescription()).append("; \n");
        }
        if (!status.isEmptyValue()) {
            builder.append(Status.FIELD_NAME).append(": ").append(getStatus()).append("; \n");
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append(Tag.FIELD_NAME).append("s: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
