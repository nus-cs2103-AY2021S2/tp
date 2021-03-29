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

        return (firstStart.isBefore(secondStart) && secondStart.isBefore(firstEnd))
                || (secondStart.isBefore(firstStart) && firstStart.isBefore(secondEnd));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getEntryName())
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
