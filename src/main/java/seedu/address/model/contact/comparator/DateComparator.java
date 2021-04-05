package seedu.address.model.contact.comparator;

import java.sql.Timestamp;
import java.util.Comparator;

import seedu.address.model.contact.Contact;

public class DateComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact p1, Contact p2) {
        Timestamp t1 = p1.getTimeAdded().getTimestamp();
        Timestamp t2 = p2.getTimeAdded().getTimestamp();

        if (t1.before(t2)) {
            return -1;
        } else if (t1.after(t2)) {
            return 1;
        } else {
            return 0;
        }
    }
}
