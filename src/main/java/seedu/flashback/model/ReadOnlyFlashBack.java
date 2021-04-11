package seedu.flashback.model;

import javafx.collections.ObservableList;
import seedu.flashback.model.flashcard.Flashcard;

/**
 * Unmodifiable view of FlashBack
 */
public interface ReadOnlyFlashBack {

    /**
     * Returns an unmodifiable view of the cards list.
     * This list will not contain any duplicate cards.
     */
    ObservableList<Flashcard> getCardList();

}
