package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFlashBack {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Flashcard> getPersonList();

}
