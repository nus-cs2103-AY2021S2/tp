package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.contact.Contact;

/**
 * Unmodifiable view of an contact list.
 */
public interface ReadOnlyContactsList {

    /**
     * Returns an unmodifiable view of the contact list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

}
