package seedu.address.model.entry;

import java.util.Comparator;

public class EntryComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry a, Entry b) {
        if (a.getStartDate().isBefore(b.getStartDate())) {
            return -1;
        } else {
            return 1;
        }
    }

}
