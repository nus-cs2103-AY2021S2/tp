package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

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
     * Returns a view of the food list.
     *
     * @return food list
     */
    UniqueFoodList getFoodList();

}
