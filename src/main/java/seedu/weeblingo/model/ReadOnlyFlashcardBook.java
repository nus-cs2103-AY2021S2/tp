package seedu.weeblingo.model;

import javafx.collections.ObservableList;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;

/**
 * Unmodifiable view of an flashcard book
 */
public interface ReadOnlyFlashcardBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Flashcard> getFlashcardList();

    ObservableList<Score> getScoreHistoryList();
}
