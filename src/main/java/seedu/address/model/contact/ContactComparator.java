package seedu.address.model.contact;

import java.util.Comparator;

/**
 * Compares two contacts based on their names.
 */
public class ContactComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact contact, Contact otherContact) {
        return contact.getName().fullName.compareTo(otherContact.getName().fullName);
    }
}
