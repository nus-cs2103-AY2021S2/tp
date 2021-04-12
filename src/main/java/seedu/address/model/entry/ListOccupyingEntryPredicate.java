package seedu.address.model.entry;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that an {@code Entry}'s start and end dates matches the target interval given.
 */
public class ListOccupyingEntryPredicate implements Predicate<Entry> {

    private final LocalDateTime targetStart;
    private final LocalDateTime targetEnd;

    public ListOccupyingEntryPredicate(EntryDate startDateTime, EntryDate endDateTime) {
        this.targetStart = startDateTime.getDate();
        this.targetEnd = endDateTime.getDate();
    }

    @Override
    public boolean test(Entry entry) {
        LocalDateTime entryStart = entry.getStartDate();
        LocalDateTime entryEnd = entry.getEndDate();
        return (targetStart.isBefore(entryEnd) && entryStart.isBefore(targetEnd));
    }
}

