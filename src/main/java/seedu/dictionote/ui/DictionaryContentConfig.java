package seedu.dictionote.ui;

import seedu.dictionote.model.dictionary.DisplayableContent;

public interface DictionaryContentConfig {
    void setDisplayContent(DisplayableContent displayableContent);
    boolean isContentVisible();
}
