package seedu.address.commons.core;

import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.Objects;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.comparator.DateComparator;
import seedu.address.model.contact.comparator.NameComparator;

public class AddressBookSettings implements Serializable {
    private String comparator;

    /**
     * Constructs an {@code AddressBookSettings} with the default chronological sort.
     */
    public AddressBookSettings() {
        this.comparator = OPTION_DATE;
    }

    /**
     * Constructs an {@code AddressBookSettings} with the specified sort.
     */
    public AddressBookSettings(String comparator) {
        this.comparator = OPTION_DATE;
    }

    public Comparator<Contact> getComparator() {
        switch (comparator) {
        case OPTION_DATE:
            return new DateComparator();
        case OPTION_NAME:
            return new NameComparator();
        default:
            throw new InvalidParameterException();
        }
    }

    public void setComparator(String comparator) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comparator : " + comparator);
        return sb.toString();
    }
}
