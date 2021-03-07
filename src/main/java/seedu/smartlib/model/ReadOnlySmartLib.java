package seedu.smartlib.model;

import javafx.collections.ObservableList;
import seedu.smartlib.model.reader.Reader;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySmartLib {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Reader> getReaderList();

}
