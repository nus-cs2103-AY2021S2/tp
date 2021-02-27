package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.person.Person;

/**
 * Unmodifiable view of an dictionote book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
