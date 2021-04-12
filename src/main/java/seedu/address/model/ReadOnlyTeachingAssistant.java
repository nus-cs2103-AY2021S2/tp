package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.entry.Entry;

/**
 * Unmodifiable view of Teaching Assistant.
 */
public interface ReadOnlyTeachingAssistant {

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

    /**
     * Returns an unmodifiable view of the entries list.
     * This list will not contain any overlapping entries.
     */
    ObservableList<Entry> getEntryList();
}
