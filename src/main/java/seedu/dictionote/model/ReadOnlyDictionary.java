package seedu.dictionote.model;

import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.Content;

public interface ReadOnlyDictionary {

    ObservableList<Content> getContentList();
}
