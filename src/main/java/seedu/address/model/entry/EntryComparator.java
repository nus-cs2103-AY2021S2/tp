package seedu.address.model.entry;

import java.util.Comparator;

/**
 * Compares two entries based on their date times.
 */
public class EntryComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry entry, Entry otherEntry) {
        if (entry.getStartDate().isBefore(otherEntry.getStartDate())) {
            return -1;
        } else {
            return 1;
        }
    }
}
