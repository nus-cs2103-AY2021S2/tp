package seedu.smartlib.model;

import javafx.collections.ObservableList;
import seedu.smartlib.model.person.Reader;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Reader> getPersonList();

}
