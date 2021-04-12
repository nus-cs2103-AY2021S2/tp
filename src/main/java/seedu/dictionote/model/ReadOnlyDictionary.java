package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.Content;

/**
 * Unmodifiable view of dictionary.
 */
public interface ReadOnlyDictionary {

    /**
     * Returns an unmodifiable view of the content list.
     * This list will not contain any duplicate contents.
     */
    ObservableList<Content> getContentList();
}
