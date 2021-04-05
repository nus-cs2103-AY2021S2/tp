package seedu.address.commons.core;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.comparator.DateComparator;

public class AddressBookSettings implements Serializable {
    private Comparator<Contact> comparator;

    public AddressBookSettings() {
        this.comparator = new DateComparator();
    }

    public AddressBookSettings(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Contact> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddressBookSettings)) { //this handles null as well.
            return false;
        }

        AddressBookSettings o = (AddressBookSettings) other;

        return comparator.equals(o.comparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comparator);
    }
}
