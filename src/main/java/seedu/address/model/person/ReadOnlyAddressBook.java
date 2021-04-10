package seedu.address.model.person;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns a person by name inside the Addresbook.
     * Returns null if person not found.
     * @param name
     * @return
     */
    public Person getPersonByName(PersonName name);
}
