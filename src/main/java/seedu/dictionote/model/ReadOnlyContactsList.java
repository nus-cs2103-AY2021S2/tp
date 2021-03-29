package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.contact.Contact;

/**
 * Unmodifiable view of an dictionote book
 */
public interface ReadOnlyContactsList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Contact> getContactList();

}
