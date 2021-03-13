package seedu.address.model.person;

import javafx.collections.ObservableList;
import seedu.address.model.Book;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPersonBook extends Book {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
