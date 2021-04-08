package seedu.address.model.contact.comparator;

import java.util.Comparator;

import seedu.address.model.contact.Contact;

public class NameComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact p1, Contact p2) {
        return p1.compareTo(p2);
    }
}
