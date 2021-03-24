package seedu.address.model.schedule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Schedule in Teaching Assistant
 */
public class Schedule {

    private final ScheduleDescription scheduleDescription;
    private final DateTime startDate;
    private final DateTime endDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null
     */
    public Schedule(ScheduleDescription scheduleDescription, DateTime startDate,
                    DateTime endDate, Set<Tag> tags) {
        requireAllNonNull(scheduleDescription, startDate, endDate, tags);
        this.scheduleDescription = scheduleDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags.addAll(tags);
    }

    public ScheduleDescription getScheduleDescription() {
        return scheduleDescription;
    }

    public LocalDateTime getStartDate() {
        return startDate.getDateTime();
    }

    public LocalDateTime getEndDate() {
        return endDate.getDateTime();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both schedules have the same name.
     * This defines a weaker notion of equality between two schedules.
     */
    public boolean isSameSchedule(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        return otherSchedule != null
                && otherSchedule.getScheduleDescription().equals(getScheduleDescription());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getScheduleDescription())
                .append("; Start Date: ")
                .append(getStartDate())
                .append("; End Date: ")
                .append(getEndDate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
