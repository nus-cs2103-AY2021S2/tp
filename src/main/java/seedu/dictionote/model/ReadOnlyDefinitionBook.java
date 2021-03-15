package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.Definition;

public interface ReadOnlyDefinitionBook {

    ObservableList<Definition> getDefinitionList();
}
