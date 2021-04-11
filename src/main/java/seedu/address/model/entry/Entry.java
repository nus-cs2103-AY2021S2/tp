package seedu.address.model.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Entry in Teaching Assistant
 */
public class Entry {

    private final EntryName entryName;
    private final EntryDate startDate;
    private final EntryDate endDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null
     */
    public Entry(EntryName entryName, EntryDate startDate,
                 EntryDate endDate, Set<Tag> tags) {
        requireAllNonNull(entryName, startDate, endDate, tags);
        this.entryName = entryName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags.addAll(tags);
    }

    public EntryName getEntryName() {
        return entryName;
    }

    public EntryDate getOriginalStartDate() {
        return startDate;
    }

    public EntryDate getOriginalEndDate() {
        return endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate.getDate();
    }

    public LocalDateTime getEndDate() {
        return endDate.getDate();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both entries have the same fields
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Entry)) {
            return false;
        }

        Entry otherEntry = (Entry) other;
        return otherEntry.getEntryName().equals(getEntryName())
                && otherEntry.getOriginalStartDate().equals(getOriginalStartDate())
                && otherEntry.getOriginalEndDate().equals(getOriginalEndDate())
                && otherEntry.getTags().equals(getTags());
    }
    /**
     * Returns true if both entries are the same objects.
     */
    public boolean isSameEntry(Entry otherEntry) {
        return otherEntry == this;
    }

    /**
     * Returns true if both entries have overlapping time intervals.
     */
    public boolean overlapsWith(Entry otherEntry) {
        if (otherEntry == null) {
            return true;
        }

        LocalDateTime firstStart = this.getStartDate();
        LocalDateTime firstEnd = this.getEndDate();
        LocalDateTime secondStart = otherEntry.getStartDate();
        LocalDateTime secondEnd = otherEntry.getEndDate();


        return (firstStart.isBefore(secondEnd) && secondStart.isBefore(firstEnd));
    }

    /**
     * Returns true if the current date is after end date.
     */
    public boolean isOverdue() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(getEndDate());
    }

    /**
     * Returns true if start date is different from end date.
     */
    public boolean haveDifferentDates() {
        return !getStartDate().isEqual(getEndDate());
    }

    /**
     * Returns the string representation of the start date and time
     * formatted with the default formatter.
     */
    public String startTimestamp() {
        return getStartDate().format(EntryDate.DEFAULT_FORMATTER);
    }

    /**
     * Returns the string representation of the end date and time
     * formatted with the default formatter.
     */
    public String endTimestamp() {
        return getEndDate().format(EntryDate.DEFAULT_FORMATTER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEntryName())
                .append(haveDifferentDates() ? new StringBuilder("; Start Date: ").append(startTimestamp()) : "")
                .append("; End Date: ")
                .append(endTimestamp());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
