package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Modifiable view of an address book
 */
public interface ModifiableAddressBook {

    /**
     * Returns an modifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getModifiablePersonList();

}
