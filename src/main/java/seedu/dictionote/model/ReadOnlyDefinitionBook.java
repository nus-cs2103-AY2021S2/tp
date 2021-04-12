package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.Definition;

/**
 * Unmodifiable view of definition book.
 */
public interface ReadOnlyDefinitionBook {

    /**
     * Returns an unmodifiable view of the definition list.
     * This list will not contain any duplicate definitions.
     */
    ObservableList<Definition> getDefinitionList();
}
