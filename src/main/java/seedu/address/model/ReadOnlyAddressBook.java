package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.entry.Entry;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the entries list.
     * This list will not contain any overlapping entries.
     */
    ObservableList<Entry> getEntryList();
}
