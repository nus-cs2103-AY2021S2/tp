package seedu.address.model.entry;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Ensures that entries occupying the interval provided by user are being listed out.
 */
public class ListOccupyingEntryPredicate implements Predicate<Entry> {

    private final LocalDateTime targetStart;
    private final LocalDateTime targetEnd;

    /**
     * Constructs a predicate used to check if entries fall within the target interval.
     * @param startDateTime the starting of the interval
     * @param endDateTime the ending of the interval
     */
    public ListOccupyingEntryPredicate(EntryDate startDateTime, EntryDate endDateTime) {
        this.targetStart = startDateTime.getDate();
        this.targetEnd = endDateTime.getDate();
    }

    /**
     * Returns true if entry's start and end time falls within target interval.
     */
    @Override
    public boolean test(Entry entry) {
        LocalDateTime entryStart = entry.getStartDate();
        LocalDateTime entryEnd = entry.getEndDate();
        return (targetStart.isBefore(entryEnd) && entryStart.isBefore(targetEnd));
    }
}

