package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Task {

    private final TaskDescription taskDescription;
    private final LocalDate date;
    private final Set<Tag> tags = new HashSet<>();

    public Task(TaskDescription taskDescription, LocalDate date, Set<Tag> tags) {
        requireAllNonNull(taskDescription, date, tags);
        this.taskDescription = taskDescription;
        this.date = date;
        this.tags.addAll(tags);
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getTaskDescription().equals(getTaskDescription());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTaskDescription())
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
